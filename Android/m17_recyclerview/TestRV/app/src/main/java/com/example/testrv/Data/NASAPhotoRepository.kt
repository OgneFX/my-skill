package Data



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.nasa.gov"

val z : ArrayList<Photos> = ArrayList()

val retrofit = Retrofit.Builder()

    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(SearchDataApi::class.java)


interface SearchDataApi {
    @GET("/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=aSzPmTosEs0t2MJZhs8KD4IcLV45emgGEt2mPGj6")
    suspend fun nasaPhoto(): NasaDto

}



