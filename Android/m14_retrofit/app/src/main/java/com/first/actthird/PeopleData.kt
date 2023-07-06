package com.first.actthird


import com.google.gson.annotations.SerializedName


data class PeopleData(

    @SerializedName("results") var results: ArrayList<Results> = arrayListOf(),
    @SerializedName("info") var info: Info? = Info()
)


data class Name(

    @SerializedName("title") var title: String? = null,
    @SerializedName("first") var first: String? = null,
    @SerializedName("last") var last: String? = null

)


data class Street(

    @SerializedName("number") var number: Int? = null,
    @SerializedName("name") var name: String? = null

)


data class Coordinates(

    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null

)


data class Timezone(

    @SerializedName("offset") var offset: String? = null,
    @SerializedName("description") var description: String? = null

)


data class Location(

    @SerializedName("street") var street: Street? = Street(),
    @SerializedName("city") var city: String? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("postcode") var postcode: Int? = null,
    @SerializedName("coordinates") var coordinates: Coordinates? = Coordinates(),
    @SerializedName("timezone") var timezone: Timezone? = Timezone()

)


data class Login(

    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("salt") var salt: String? = null,
    @SerializedName("md5") var md5: String? = null,
    @SerializedName("sha1") var sha1: String? = null,
    @SerializedName("sha256") var sha256: String? = null

)

data class Dob(

    @SerializedName("date") var date: String? = null,
    @SerializedName("age") var age: Int? = null

)


data class Registered(

    @SerializedName("date") var date: String? = null,
    @SerializedName("age") var age: Int? = null

)


data class Id(

    @SerializedName("name") var name: String? = null,
    @SerializedName("value") var value: String? = null

)


data class Picture(

    @SerializedName("large") var large: String? = null,
    @SerializedName("medium") var medium: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null

)


data class Results(

    @SerializedName("gender") var gender: String? = null,
    @SerializedName("name") var name: Name? = Name(),
    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("email") var email: String? = null,
    @SerializedName("login") var login: Login? = Login(),
    @SerializedName("dob") var dob: Dob? = Dob(),
    @SerializedName("registered") var registered: Registered? = Registered(),
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("cell") var cell: String? = null,
    @SerializedName("id") var id: Id? = Id(),
    @SerializedName("picture") var picture: Picture? = Picture(),
    @SerializedName("nat") var nat: String? = null
)

data class Info(
    @SerializedName("seed") val seed: String? = null,
    @SerializedName("results") val results: Int? = null,
    @SerializedName("page") val page: Int? = null,
    @SerializedName("version") val version: String? = null
)


