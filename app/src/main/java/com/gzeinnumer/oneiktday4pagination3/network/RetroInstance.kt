package com.gzeinnumer.oneiktday4pagination3.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//todo 3
class RetroInstance {

    companion object {
        val baseURL = "https://rickandmortyapi.com/api/"

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}