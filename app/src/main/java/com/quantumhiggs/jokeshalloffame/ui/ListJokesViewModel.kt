package com.quantumhiggs.jokeshalloffame.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.jokeshalloffame.model.Jokes
import com.quantumhiggs.jokeshalloffame.model.Value
import com.quantumhiggs.jokeshalloffame.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListJokesViewModel : ViewModel() {

    private var listJokes = MutableLiveData<Jokes>()
    private var listValue = MutableLiveData<ArrayList<Value>>()

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
            .enqueue(object : Callback<Value> {
                override fun onFailure(call: Call<Value>, t: Throwable) {

                }

                override fun onResponse(call: Call<Value>, response: Response<Value>) {
                    if (response.isSuccessful) {
                        response.body()?.let { listValue.value?.add(it) }
                    }
                }
            })
    }

    fun setListJokes(): MutableLiveData<Jokes> {
        return listJokes
    }

    fun setJoke(): MutableLiveData<ArrayList<Value>> {
        getJoke()
        return listValue
    }

}