package com.sehoul.rickandmortyapi

import com.google.gson.annotations.SerializedName

data class RickandMortyDataNameResponse(
    @SerializedName("results") val results: List<RickandMortyDataResponse>
)


