package com.quantumhiggs.jokeshalloffame.network

import android.telecom.Call
import com.quantumhiggs.jokeshalloffame.model.Jokes
import retrofit2.http.GET

interface ApiServices {

    @GET("random/10")
    fun getAllLeagues(): Call<Jokes>

}