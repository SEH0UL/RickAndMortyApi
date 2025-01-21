package com.sehoul.rickandmortyapi

import com.google.gson.annotations.SerializedName

data class RickandMortyDataResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val origin: RickandMortyOriginResponse,
    @SerializedName("location") val location: RickandMortyLocationResponse,
    @SerializedName("image") val image: String,
    @SerializedName("url") val url: String,
)

data class RickandMortyOriginResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String?
)

data class RickandMortyLocationResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
