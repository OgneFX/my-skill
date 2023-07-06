package Data

import com.google.gson.annotations.SerializedName

data class NasaDto(
    @SerializedName("photos") var photos: List<Photos> = listOf()
)

data class Camera(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("rover_id") var roverId: Int? = null,
    @SerializedName("full_name") var fullName: String? = null

)


data class Rover(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("landing_date") var landingDate: String? = null,
    @SerializedName("launch_date") var launchDate: String? = null,
    @SerializedName("status") var status: String? = null

)

data class Photos(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("sol") var sol: Int? = null,
    @SerializedName("camera") var camera: Camera? = Camera(),
    @SerializedName("img_src") var imgSrc: String? = null,
    @SerializedName("earth_date") var earthDate: String? = null,
    @SerializedName("rover") var rover: Rover? = Rover()

)
