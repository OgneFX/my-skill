package com.first.actthird


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


import retrofit2.http.GET

private const val BASE_URL = "https://randomuser.me"

object SearchAPI {


    private val retrofit = Retrofit.Builder()

        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    val searchDataApi:SearchDataApi = retrofit.create(SearchDataApi::class.java)
}

interface SearchDataApi {
    @GET("/api")
   suspend fun getPeopleInfo(): Response<PeopleData>

}