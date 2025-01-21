package com.sehoul.rickandmortyapi

import android.adservices.adid.AdId
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//    https://rickandmortyapi.com/api/character/?name=jerry

// Usamos @Query en lugar de @Path porque 'name' es un parámetro de consulta
    @GET("api/character/")
    suspend fun getRickandMorty(@Query("name") rickandmortyName: String): Response<RickandMortyDataResponse>

    @GET("api/character/")
    suspend fun getRickandMortyDetail(@Path("id") rickandmortyId:Int):Response<RickandMortyDataResponse>


}