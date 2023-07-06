package Data

import Entity.UsefulActivity
import com.google.gson.annotations.SerializedName

data class UsefulActivityDto(
    @SerializedName("activity") override var activity: String,
    @SerializedName("type")override var type: String,
    @SerializedName("participants")override var participants: Int,
    @SerializedName("price")override var price: Float,
    @SerializedName("link")override var link: String,
    @SerializedName("key")override var key: Int,
    @SerializedName("accessibility")override var accessibility: Float

) : UsefulActivity {


}