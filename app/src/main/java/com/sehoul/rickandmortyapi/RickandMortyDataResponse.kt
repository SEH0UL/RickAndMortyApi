package com.sehoul.rickandmortyapi

import com.google.gson.annotations.SerializedName

data class RickandMortyDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroes: List<RickandMortyItemResponse>
)
data class RickandMortyItemResponse(
    @SerializedName("id") val superheroId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val superheroImage:RickandMortyImageResponse
)
data class RickandMortyImageResponse(@SerializedName("url") val url:String)