package com.quantumhiggs.jokeshalloffame.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.jokeshalloffame.model.Jokes
import com.quantumhiggs.jokeshalloffame.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListJokesViewModel : ViewModel() {

    private var listJokes = MutableLiveData<Jokes>()
    private var listValue = MutableLiveData<Jokes>()

    init {
        getListJokes()
    }

    private fun getListJokes() {
        NetworkConfig()
            .api()
            .getJokes()
            .enqueue(object : Callback<Jokes> {
                override fun onFailure(call: Call<Jokes>, t: Throwable) {
                    listJokes.value = null
                }

                override fun onResponse(call: Call<Jokes>, response: Response<Jokes>) {
                    if (response.isSuccessful) {
                        listJokes.value = response.body()
                    } else {
                        listJokes.value = null
                    }
                }
            })
    }

    private fun getJoke() {
        NetworkConfig()
            .api()
            .getJoke()
            .enqueue(object : Callback<Jokes> {
                override fun onFailure(call: Call<Jokes>, t: Throwable) {
                    Log.e("onFailure : ", t.localizedMessage)
                }

                override fun onResponse(call: Call<Jokes>, response: Response<Jokes>) {
                    if (response.isSuccessful) {
                        listValue.value = response.body()
                    } else {
                        Log.e("onResponse : ", response.message())
                    }
                }
            })
    }

    fun newInstance() : MutableLiveData<Jokes> {
        return MutableLiveData()
    }

    fun setListJokes(): MutableLiveData<Jokes> {
        return listJokes
    }

    fun setJoke(): MutableLiveData<Jokes> {
        getJoke()
        return listValue
    }

    fun getOneJoke(): MutableLiveData<Jokes> {
        return listValue
    }

}