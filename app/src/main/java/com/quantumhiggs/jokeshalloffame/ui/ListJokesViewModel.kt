package com.quantumhiggs.jokeshalloffame.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantumhiggs.jokeshalloffame.model.Jokes
import com.quantumhiggs.jokeshalloffame.model.Value
import com.quantumhiggs.jokeshalloffame.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class ListJokesViewModel : ViewModel() {

    private var listJokes = MutableLiveData<Jokes>()
    private var listValue = MutableLiveData<Value>()

    init {
        getListJokes()
        getJoke()
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
                        listValue.value = response.body()
                    }
                }
            })
    }

    fun setListJokes(): MutableLiveData<Jokes> {

        val arr = listJokes.value?.value as ArrayList<Jokes>

//        for((index,data) in arr.withIndex()) {
//            arr.sortedBy {
//                arr.get(0).value.
//            }
//        }

        return listJokes
    }

    fun setVote(vote : Int, pos : Int) : MutableLiveData<Jokes> {
//        var joke = listJokes.value?.value
//        var temp : Value?
//        joke?.get(pos)?.vote = vote
//        if (joke?.get(pos)?.vote ?: 0 > joke?.get(pos - 1)?.vote ?: 0) {
//            temp = joke?.get(pos)
//            joke?.get(pos)?.joke = joke?.get(pos - 1)!!.joke
//            joke.get(pos - 1).joke = temp!!.joke
//        }
//        Collections.swap(listJokes as MutableList<*>,listJokes.)
        return listJokes
    }

    fun setJoke(): MutableLiveData<Value> {

//        listJokes.value?.value?.get(listJokes.value?.value?.size ?: 0 + 1) = listValue.value

        return listValue
    }

}