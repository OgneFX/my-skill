package com.example.finalskillcinema.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import com.example.finalskillcinema.data.ParamsFilterFilm
import com.example.finalskillcinema.api.old.filmbyfilter.FilterCountry
import com.example.finalskillcinema.api.old.filmbyfilter.FilterGenre
import com.example.finalskillcinema.api.old.personbyname.ItemPerson
import com.example.finalskillcinema.domain.GetFilmListUseCase
import com.example.finalskillcinema.domain.GetGenresCountriesUseCase
import com.example.finalskillcinema.domain.GetPersonByNameUseCase
import com.example.finalskillcinema.entity.FilterCountryGenre
import com.example.finalskillcinema.entity.MenuItem
import com.example.finalskillcinema.ui.search.filmadapter.FilmsByFilterPagingSource
import com.example.finalskillcinema.ui.search.personadapter.PersonsPagingSource
import javax.inject.Inject

@HiltViewModel
class ViewModelSearch @Inject constructor(
    private val getFilmListUseCase: GetFilmListUseCase,
    private val getGenresCountriesUseCase: GetGenresCountriesUseCase,
    private val getPersonByNameUseCase: GetPersonByNameUseCase
) : ViewModel() {

    private val _filterFlow = MutableStateFlow(ParamsFilterFilm())
    val filterFlow = _filterFlow.asStateFlow()

    private lateinit var countriesList: List<FilterCountry>
    private lateinit var genresList: List<FilterGenre>

    private val _filterValuesCountriesGenres =
        MutableStateFlow<List<FilterCountryGenre>>(emptyList())
    val filterValuesCountriesGenres = _filterValuesCountriesGenres.asStateFlow()

    val films: Flow<PagingData<MenuItem>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            FilmsByFilterPagingSource(
                filters = _filterFlow.value,
                getFilmListUseCase = getFilmListUseCase
            )
        }
    ).flow.cachedIn(viewModelScope)

    val persons: Flow<PagingData<ItemPerson>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            PersonsPagingSource(
                getPersonByNameUseCase = getPersonByNameUseCase,
                personName = _filterFlow.value.keyword
            )
        }
    ).flow.cachedIn(viewModelScope)

    val newPersons: Flow<PagingData<ItemPerson>> = getPersonByNameUseCase
        .newExecutePerson(personName = _filterFlow.value.keyword).cachedIn(viewModelScope)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getGenresCountriesUseCase.executeGenresCountries()
            countriesList = response.countries.sortedBy { it.name }.filter { it.name.isNotEmpty() }
            genresList = response.genres.sortedBy { it.name }.filter { it.name.isNotEmpty() }
        }
    }

    fun getFiltersFull() = _filterFlow.value

    fun updateFiltersFull(filterFilm: ParamsFilterFilm) {
        viewModelScope.launch {
            if (_filterFlow.value != filterFilm) _filterFlow.value = filterFilm
        }
    }

    fun updateFilterCountriesGenres(type: String, keyword: String) {
        _filterValuesCountriesGenres.value = when (type) {
            KEY_COUNTRY -> {
                countriesList.filter { it.name.startsWith(keyword, ignoreCase = true) }
            }

            KEY_GENRE -> {
                genresList.filter { it.name.startsWith(keyword, ignoreCase = true) }
            }

            else -> {
                emptyList()
            }
        }
    }

    fun setFilterValues(filterType: String) {
        when (filterType) {
            KEY_COUNTRY -> _filterValuesCountriesGenres.value = countriesList
            KEY_GENRE -> _filterValuesCountriesGenres.value = genresList
        }
    }

    companion object {
        private const val TAG = "ViewModelSearch"

        private const val KEY_COUNTRY = "country"
        private const val KEY_GENRE = "genre"
    }
}