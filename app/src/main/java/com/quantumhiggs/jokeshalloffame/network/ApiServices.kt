package com.quantumhiggs.jokeshalloffame.network

import com.quantumhiggs.jokeshalloffame.model.Jokes
import com.quantumhiggs.jokeshalloffame.model.Value
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("random/10")
    fun getJokes(): Call<Jokes>

    @GET("random/1")
    fun getJoke(): Call<Value>

}