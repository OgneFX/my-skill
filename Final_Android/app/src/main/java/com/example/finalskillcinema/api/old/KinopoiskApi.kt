package com.example.finalskillcinema.api.old

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.finalskillcinema.api.AuthInterceptor
import com.example.finalskillcinema.api.old.filmbyfilter.ResponseByFilter
import com.example.finalskillcinema.api.old.filmbyfilter.ResponseGenresCountries
import com.example.finalskillcinema.api.old.personbyname.ResponsePersonByName

interface KinopoiskApi {

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
    ): ResponseByFilter

    @GET("v2.2/films/filters")
    suspend fun getGenresCountries(): ResponseGenresCountries


    @GET("v1/persons")
    suspend fun getPersonByName(
        @Query("name") name: String,
        @Query("page") page: Int
    ): ResponsePersonByName
    companion object {
        private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"
        private const val API_KEY = "1e1c335a-d58a-462f-8292-84d278aa15d6"

        private val interceptor = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(API_KEY))
            .build()

        fun getInstance(): KinopoiskApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(interceptor)
                .build()
                .create(KinopoiskApi::class.java)
        }
    }
}