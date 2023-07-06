package com.example.finalskillcinema.ui.filmdetail

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalskillcinema.R
import com.example.finalskillcinema.app.loadImage
import com.example.finalskillcinema.data.FilmCategories
import com.example.finalskillcinema.data.TOP_TYPES
import com.example.finalskillcinema.databinding.FragmentFilmDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.finalskillcinema.db.model.FilmPersons
import com.example.finalskillcinema.db.model.FilmWithDetailInfo
import com.example.finalskillcinema.ui.adapters.MyAdapterTypes
import com.example.finalskillcinema.ui.adapters.MyListAdapter
import com.example.finalskillcinema.ui.states.StateInfo
import com.example.finalskillcinema.ui.series.FragmentSeasons



@AndroidEntryPoint
class FragmentFilmDetail : Fragment() {
    private var _binding: FragmentFilmDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModelFilmDetail by viewModels()
    private lateinit var actorAdapter: MyListAdapter
    private lateinit var makersAdapter: MyListAdapter
    private lateinit var galleryAdapter: MyListAdapter
    private lateinit var similarAdapter: MyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: FragmentFilmDetailArgs by navArgs()
        viewModel.getFilmById(args.filmId)

        stateLoadingListener()                                      // Setup load listener


        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filmDetailInfo.collect { film ->
                    if (film != null) {
                        setFilmDetails(film)                        // Setup poster and film describe
                        setFilmPersons(film)                        // Setup film actors/makers list
                        setFilmGallery(film)                        // Setup film gallery
                        setSimilar(film)                            // Setup similar film list
                        setButtonsForDB(film.film.filmId)           // Setup buttons for DB-collections
                    }
                }
            }
        }


        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun stateLoadingListener() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadCurrentFilmState.collect { state ->
                    when (state) {
                        is StateInfo.Loading -> {
                            binding.apply {
                                filmDetail.progress = 1f

                                progressGroup.isVisible = true
                                loadingProgress.isVisible = true
                                loadingBanner.isVisible = true
                                loadingRefreshBtn.isVisible = false

                                filmName.isVisible = false
                                filmMainGroup.isVisible = false
                                filmDescriptionGroup.isVisible = false
                                myScroll.isVisible = false
                            }
                        }

                        is StateInfo.Success -> {
                            binding.apply {
                                filmDetail.progress = 0f

                                progressGroup.isVisible = false
                                loadingBanner.isVisible = false
                                loadingRefreshBtn.isVisible = false
                                loadingProgress.isVisible = false

                                filmName.isVisible = true
                                filmMainGroup.isVisible = true
                                filmDescriptionGroup.isVisible = true
                                myScroll.isVisible = true
                            }
                        }

                        else -> {
                            binding.apply {
                                filmDetail.progress = 1f

                                progressGroup.isVisible = true
                                loadingBanner.isVisible = true
                                loadingRefreshBtn.isVisible = true
                                loadingProgress.isVisible = false

                                filmName.isVisible = false
                                filmMainGroup.isVisible = false
                                filmDescriptionGroup.isVisible = false
                                myScroll.isVisible = false
                            }
                        }
                    }
                }
            }
        }
    }

    // Film details
    private fun setFilmDetails(film: FilmWithDetailInfo) {
        binding.apply {
            filmName.text = film.film.name
            filmPoster.loadImage(film.film.poster)
            filmDescriptionShort.text = film.detailInfo?.shortDescription
            filmDescriptionFull.text = film.detailInfo?.description
            filmRatingNameTv.text = getRatingName(film)
            filmYearGenresTv.text = getYearGenres(film, requireContext())
            filmCountryLengthAgeLimitTv.text = getStrCountriesLengthAge(film)

        }
        if (film.detailInfo?.type == FilmCategories.TV_SERIES.name) {
            binding.seasonsGroup.isVisible = true
            getSeriesSeasons(film)
        } else {
            binding.seasonsGroup.isVisible = false
        }
    }

    // Seasons details
    private fun getSeriesSeasons(film: FilmWithDetailInfo) {
        val seasonsCount = film.seriesEpisodes?.map { it.seriesNumber }?.toSet()?.size
        val seriesCount = film.seriesEpisodes?.size

        val seasonStr = seasonsCount?.let {
            resources.getQuantityString(R.plurals.film_details_series_count, it, it)
        }
        val episodeStr = seriesCount?.let {
            resources.getQuantityString(R.plurals.film_details_episode_count, it, it)
        }

        binding.seriesSeasonsCount.text =
            resources.getString(R.string.seasons_episodes_count, seasonStr, episodeStr)

        binding.seriesSeasonsBtn.setOnClickListener {
            onClickShowAllSeasons(film.film.filmId, film.film.name)
        }
    }

    // Persons list
    private fun setFilmPersons(film: FilmWithDetailInfo) {
        actorAdapter = MyListAdapter(
            maxListSize = 20,
            clickEndButton = {},
            clickItem = { onClickItemPerson(it) }
        )
        makersAdapter = MyListAdapter(
            maxListSize = 20,
            clickEndButton = {},
            clickItem = { onClickItemPerson(it) }
        )
        binding.filmActorsList.layoutManager =
            GridLayoutManager(
                requireContext(), MAX_ACTORS_ROWS, GridLayoutManager.HORIZONTAL, false
            )
        binding.filmMakersList.layoutManager =
            GridLayoutManager(
                requireContext(), MAX_MAKERS_ROWS, GridLayoutManager.HORIZONTAL, false
            )
        binding.filmActorsList.adapter = actorAdapter
        binding.filmMakersList.adapter = makersAdapter

        val actors = mutableListOf<FilmPersons>()
        val makers = mutableListOf<FilmPersons>()

        film.persons?.forEach {
            if (it.professionKey == "ACTOR") {
                actors.add(it)
            } else {
                makers.add(it)
            }
        }

        binding.filmActorsCount.text = actors.size.toString()
        if (actors.size < MAX_ACTORS_COLUMN * MAX_ACTORS_ROWS) {
            actorAdapter.submitList(actors.map { MyAdapterTypes.ItemFilmPerson(it) })
        } else {
            val tempActors = actors.take(MAX_ACTORS_COLUMN * MAX_ACTORS_ROWS)
            actorAdapter.submitList(tempActors.map { MyAdapterTypes.ItemFilmPerson(it) })
        }

        binding.filmActorsBtn.setOnClickListener {
            onClickShowAllPersons(
                film.film.filmId,
                "ACTOR"
            )
        }
        binding.filmActorsCount.setOnClickListener {
            onClickShowAllPersons(
                film.film.filmId,
                "ACTOR"
            )
        }

        if (makers.size < MAX_MAKERS_COLUMN * MAX_MAKERS_ROWS) {
            binding.filmMakersCount.isVisible = false
            binding.filmMakersBtn.isVisible = false
            makersAdapter.submitList(makers.map { MyAdapterTypes.ItemFilmPerson(it) })
        } else {
            binding.filmMakersCount.isVisible = true
            binding.filmMakersBtn.isVisible = true
            binding.filmMakersCount.text = makers.size.toString()
            val makersListTemp = mutableListOf<FilmPersons>()
            repeat(MAX_MAKERS_COLUMN * MAX_MAKERS_ROWS) { makersListTemp.add(makers[it]) }
            makersAdapter.submitList(makersListTemp.map { MyAdapterTypes.ItemFilmPerson(it) })
        }
        binding.filmMakersBtn.setOnClickListener { onClickShowAllPersons(film.film.filmId, "") }
        binding.filmMakersCount.setOnClickListener { onClickShowAllPersons(film.film.filmId, "") }
    }

    // Film gallery
    private fun setFilmGallery(film: FilmWithDetailInfo) {
        binding.filmGalleryCount.text = film.gallery.size.toString()

        galleryAdapter = MyListAdapter(20, {}) {}
        binding.filmGalleryList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.filmGalleryList.adapter = galleryAdapter

        val temp = film.gallery.take(10)
        galleryAdapter.submitList(temp.map { MyAdapterTypes.ItemFilmImage(it) })

        binding.filmGalleryCount.setOnClickListener { onClickShowAllGallery(film.film.filmId) }
        binding.filmGalleryBtn.setOnClickListener { onClickShowAllGallery(film.film.filmId) }
    }

    // Similar films
    private fun setSimilar(film: FilmWithDetailInfo) {
        similarAdapter =
            MyListAdapter(
                20,
                { onClickShowAllSimilar(film.film.filmId) },
                { onClickSimilarItem(it) })
        binding.filmSimilarList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.filmSimilarList.adapter = similarAdapter

        if (film.similar != null) {
            binding.filmSimilarCount.text = film.similar.size.toString()
            similarAdapter.submitList(film.similar.map { MyAdapterTypes.ItemFilmSimilar(it) })
        } else binding.filmSimilarCount.isVisible = false


        binding.filmSimilarCount.setOnClickListener { onClickShowAllSimilar(film.film.filmId) }
        binding.filmSimilarBtn.setOnClickListener { onClickShowAllSimilar(film.film.filmId) }
    }

    // ClickListeners
    private fun onClickShowAllSeasons(filmId: Int, filmName: String) {
        findNavController().navigate(
            R.id.action_fragmentFilmDetail_to_fragmentSeries,
            bundleOf(
                FragmentSeasons.KEY_SEASON_ID to filmId,
                FragmentSeasons.KEY_SERIES_NAME to filmName
            )
        )
    }

    private fun onClickShowAllPersons(filmId: Int, professionKey: String) {
        findNavController().navigate(
            R.id.action_fragmentFilmDetail_to_fragmentPersonsByFilm,
            bundleOf(
                FragmentPersonsByFilm.KEY_ALL_STAFF_FILM to filmId,
                FragmentPersonsByFilm.KEY_ALL_STAFF_PROFESSION to professionKey
            )
        )
    }

    private fun onClickItemPerson(personId: Int) {
        val action =
            FragmentFilmDetailDirections.actionFragmentFilmDetailToFragmentPersonDetail(personId)
        findNavController().navigate(action)
    }

    private fun onClickShowAllGallery(filmId: Int) {
        val action = FragmentFilmDetailDirections
            .actionFragmentFilmDetailToFragmentGallery(filmId)
        findNavController().navigate(action)
    }

    private fun onClickShowAllSimilar(filmId: Int) {
        val action =
            FragmentFilmDetailDirections.actionFragmentFilmDetailToFragmentSimilarFilms(filmId)
        findNavController().navigate(action)
    }

    private fun onClickSimilarItem(filmId: Int) = viewModel.getFilmById(filmId)

    private fun setButtonsForDB(filmId: Int) {
        var isInFavorite = 0
        var isInBookmark = 0
        var isViewed = 0

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.checkFilmInDB(filmId).collect { markers ->
                    if (markers != null) {
                        isInFavorite = markers.isFavorite
                        isInBookmark = markers.inCollection
                        isViewed = markers.isViewed
                        if (isInFavorite == 1) binding.btnToFavorite.setColorFilter(Color.BLUE)
                        else binding.btnToFavorite.setColorFilter(Color.parseColor("#B5B5C9"))
                        if (isInBookmark == 1) binding.btnToBookmark.setColorFilter(Color.BLUE)
                        else binding.btnToBookmark.setColorFilter(Color.parseColor("#B5B5C9"))
                        if (isViewed == 1) binding.btnIsViewed.setColorFilter(Color.BLUE)
                        else binding.btnIsViewed.setColorFilter(Color.parseColor("#B5B5C9"))
                    }
                }
            }
        }
        binding.btnToFavorite.setOnClickListener {
            isInFavorite = if (isInFavorite == 1) 0 else 1
            viewModel.updateFilmMarkers(filmId, isInFavorite, isInBookmark, isViewed)
        }
        binding.btnToBookmark.setOnClickListener {
            isInBookmark = if (isInBookmark == 1) 0 else 1
            viewModel.updateFilmMarkers(filmId, isInFavorite, isInBookmark, isViewed)
        }
        binding.btnIsViewed.setOnClickListener {
            isViewed = if (isViewed == 1) 0 else 1
            viewModel.updateFilmMarkers(filmId, isInFavorite, isInBookmark, isViewed)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val MAX_ACTORS_COLUMN = 5
        private const val MAX_ACTORS_ROWS = 4
        private const val MAX_MAKERS_COLUMN = 3
        private const val MAX_MAKERS_ROWS = 2

        private fun getRatingName(film: FilmWithDetailInfo): String {
            val result = mutableListOf<String>()

            val tempRating = film.film.rating
            val rating = if (tempRating != null) {
                if (tempRating.contains("%")) {
                    (tempRating.substringBefore(".").removeSuffix(".").toInt() / 10).toString()
                } else tempRating
            } else null

            if (rating != null) result.add(rating)
            val name = film.film.name
            if (name.isNotEmpty()) result.add(name)
            return result.joinToString(", ")
        }

        private fun getYearGenres(film: FilmWithDetailInfo, context: Context): String {
            val result = mutableListOf<String>()

            if (film.detailInfo?.type == TOP_TYPES.getValue(FilmCategories.TV_SERIES)) {
                val tempStr = mutableListOf<String>()
                if (film.detailInfo.startYear != null && film.detailInfo.endYear != null) {
                    tempStr.add(film.detailInfo.startYear.toString())
                    tempStr.add(film.detailInfo.endYear.toString())
                } else {
                    if (film.detailInfo.startYear != null) tempStr.add(film.detailInfo.startYear.toString())
                    else context.getString(R.string.placeholder_series_start_year)
                    if (film.detailInfo.endYear != null) tempStr.add(film.detailInfo.endYear.toString())
                    else context.getString(R.string.placeholder_series_end_year)
                }
                result.add(tempStr.joinToString("-"))
            } else {
                if (film.detailInfo?.year != null) result.add(film.detailInfo.year.toString())
            }

            if (film.genres.size > 1) {
                film.genres.take(2).map { result.add(it.genre) }
            } else if (film.genres.size == 1) {
                result.add(film.genres.first().genre)
            } else result.add("")

            return result.joinToString(", ")
        }

        private fun getStrCountriesLengthAge(film: FilmWithDetailInfo): String {
            val result = mutableListOf<String?>()

            val countries = film.countries
            val resultCountries = if (countries != null) {
                if (countries.size == 1) {
                    countries.first().country
                } else if (countries.size > 1) {
                    countries.joinToString(", ") { it.country }
                } else {
                    null
                }
            } else {
                null
            }
            result.add(resultCountries)

            val length = film.detailInfo?.length
            val resultLength = if (length != null) {
                val hours = length.div(60)
                val minutes = length.rem(60)
                "$hours ч $minutes мин"
            } else null
            result.add(resultLength)

            val resultAgeLimit = film.detailInfo?.ageLimit?.removePrefix("age")
            result.add("$resultAgeLimit+")

            return result.joinToString(", ")
        }
    }
}