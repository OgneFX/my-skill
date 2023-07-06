package Data

import Entity.UsefulActivity
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.boredapi.com/"

class UsefulActivitiesRepository {


    object SearchAPI {

        private val retrofit = Retrofit.Builder()

            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val searchDataApi: SearchDataApi = retrofit.create(SearchDataApi::class.java)
    }

    interface SearchDataApi {
        @GET("api/activity/")
        suspend fun getUsefulActivityRetro(): Response<UsefulActivityDto>

    }


   suspend fun getUsefulActivity(): UsefulActivity {
        val request = SearchAPI.searchDataApi.getUsefulActivityRetro()
        val heyhou = Helpful(
            activity = request.body()?.activity.toString(),
            type = request.body()?.type.toString(),
            participants = request.body()!!.participants,
            price = request.body()!!.price,
            link = request.body()?.link.toString(),
            key = request.body()!!.key,
            accessibility = request.body()!!.accessibility
        )

        println(heyhou.activity + "!!!!!!!!!!!!!")
       println(heyhou.type + "!!!!!!!!!!!!!")
       println(heyhou.price.toString() + "!!!!!!!!!!!!!")



        return heyhou
    }


}