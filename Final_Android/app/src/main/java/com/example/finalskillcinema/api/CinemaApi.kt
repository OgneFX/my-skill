package com.example.finalskillcinema.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.finalskillcinema.api.model.ResponseFilmsByFilter
import com.example.finalskillcinema.api.model.ResponseFilmsTop
import com.example.finalskillcinema.api.model.ResponsePersonById
import com.example.finalskillcinema.api.model.ResponsePersonByName
import com.example.finalskillcinema.api.model.ResponsePersonsByFilmId
import com.example.finalskillcinema.api.model.ResponsePremier


interface CinemaApi {

    // FragmentHome
    @GET("v2.2/films/top")
    suspend fun getFilmsTop(
        @Query("type") type: String,
        @Query("page") page: Int
    ): ResponseFilmsTop

    @GET("v2.2/films/premieres")
    suspend fun getPremier(
        @Query("year") year: Int,
        @Query("month") month: String
    ): ResponsePremier

    // ----------------------------------

    // FragmentFilmDetail
    @GET("v2.2/films/{id}")
    suspend fun getFilmById(
        @Path("id") id: Int
    ): ResponseFilmDetailById

    @GET("v1/staff")
    suspend fun getPersons(
        @Query("filmId") filmId: Int
    ): List<ResponsePersonsByFilmId>

    @GET("v2.2/films/{id}/images")
    suspend fun getFilmImages(
        @Path("id") id: Int,
        @Query("type") type: String = "STILL",
        @Query("page") page: Int
    ): ResponseGalleryByFilmId

    // FragmentFilmDetail (series)
    @GET("v2.2/films/{id}/seasons")
    suspend fun getSeasons(
        @Path("id") id: Int
    ): ResponseSeasons

    @GET("v2.2/films/{id}/similars")
    suspend fun getSimilarFilms(
        @Path("id") id: Int
    ): ResponseSimilarFilmsByFilmId

    // FragmentPersonDetail
    @GET("v1/staff/{id}")
    suspend fun getPersonById(
        @Path("id") id: Int
    ): ResponsePersonById

    // FragmentHome (TV_SERIES) & FragmentSearch
    @GET("v2.2/films/")
    suspend fun getFilmsByFilter(
        @Query("countries") countries: String,
        @Query("genres") genres: String,
        @Query("order") order: String,
        @Query("type") type: String,
        @Query("ratingFrom") ratingFrom: Int,
        @Query("ratingTo") ratingTo: Int,
        @Query("yearFrom") yearFrom: Int,
        @Query("yearTo") yearTo: Int,
        @Query("imdbId") imdbId: String?,
        @Query("keyword") keyword: String,
        @Query("page") page: Int
    ): ResponseFilmsByFilter

    @GET("v2.2/films/filters")
    suspend fun getGenresCountries(): ResponseGenresCountries

    // Fragment search (search person)
    @GET("v1/persons")
    suspend fun getPersonByName(
        @Query("name") name: String,
        @Query("page") page: Int
    ): ResponsePersonByName

    companion object {
        private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"
        private const val API_KEY = "ffcd0204-2065-4214-b6ae-aa29f5fe4003"
//        private const val API_KEY = "5d6e59d8-d24e-45f8-95c9-b5b07cb478da"
//        private const val API_KEY = "b9ebd173-2eb5-4bfd-b6e9-3226369f0a43"
//        private const val API_KEY = "00ec3c68-8c85-4bd5-8508-024db2f99a4c"
//        private const val API_KEY = "f746dfa5-8093-401b-8df2-e84042f3dc96"
//        private const val API_KEY = "4f59c6e4-9f98-4f99-a7a2-e1ac2bd61d0f"  // in case of emergency

        private val interceptor = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(API_KEY))
            .build()

        fun getInstance(): CinemaApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(interceptor)
                .build()
                .create(CinemaApi::class.java)
        }
    }
}