package com.sehoul.rickandmortyapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // CAMBIO NAME -> character
    @GET("/api/{character}")
    suspend fun getRickandMorty(@Path("character") superheroName:String): Response<RickandMortyDataResponse>

    @GET("/api/character")
    suspend fun getRickandMorty(@Path("id") superheroId:Int):Response<RickandMortyDataResponse>


}