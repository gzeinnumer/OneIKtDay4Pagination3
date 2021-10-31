package com.gzeinnumer.oneiktday4pagination3.network

import com.gzeinnumer.oneiktday4pagination3.model.RickAndMortyList
import retrofit2.http.GET
import retrofit2.http.Query

//todo 5
interface RetroService {

    //https://rickandmortyapi.com/api/character?page=1
    @GET("character")
    suspend fun getDataFromAPI(@Query("page") query: Int):RickAndMortyList
}