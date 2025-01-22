package com.sehoul.rickandmortyapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//    https://rickandmortyapi.com/api/character/?name=jerry

// Usamos @Query en lugar de @Path porque 'name' es un parámetro de consulta

    @GET("api/character/")
    suspend fun searchCharacterByName(@Query("name") name: String): Response<RickandMortyDataResponse>

    @GET("api/character/{id}")
    suspend fun getRickandMortyDetail(@Path("id") RickandMortyId: String): Response<RickandMortyDataResponse>
}