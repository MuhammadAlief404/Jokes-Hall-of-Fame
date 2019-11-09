package com.quantumhiggs.jokeshalloffame.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.quantumhiggs.jokeshalloffame.R
import com.quantumhiggs.jokeshalloffame.model.Value
import kotlinx.android.synthetic.main.activity_main.*

class ListJokesActivity : AppCompatActivity() {

    private lateinit var viewModel: ListJokesViewModel
    private var counter = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListJokesViewModel::class.java)

        main_rv_jokes.layoutManager = LinearLayoutManager(this)

        main_fab.setOnClickListener {
            if (counter < 2) {
                counter++
                main_fab.show()
            } else {
                main_fab.hide()
            }
            val tempList = ArrayList<Value>()
            val temp = viewModel.setListJokes()
            temp.value?.value?.let { it1 ->
                tempList.addAll(it1)
            }
            viewModel.setJoke().observe(this, Observer { t ->
                tempList.add(t.value[t.value.size - 1])
                showData(tempList)
            })
        }

        main_swipe_refresh.setOnRefreshListener {
            counter = 1
            main_fab.show()
            val arr = ArrayList<Value>()
            for (data in viewModel.setListJokes().value!!.value) {
                data.vote = 0
                arr.add(data)
            }
            showData(arr)
            main_swipe_refresh.isRefreshing = false
        }

        observeListJokes()
    }

    private fun showData(data: List<Value>) {
        if (data.isNotEmpty()) {
            val sortData = getSortData(data)
            main_rv_jokes.adapter = ListJokesAdapter(sortData) {
                it.vote++
                observeListJokes()
            }
        }
    }

    private fun observeListJokes() {
        if (counter < 2) {
            viewModel.setListJokes().observe(this, Observer { t ->
                showData(t.value)
            })
        } else {
            val tempList = ArrayList<Value>()
            val temp = viewModel.setListJokes()
            temp.value?.value?.let { it1 ->
                tempList.addAll(it1)
            }
            viewModel.getOneJoke().observe(this, Observer { t ->
                tempList.add(t.value[t.value.size - 1])
                showData(tempList)
            })
        }
    }

    private fun getSortData(data: List<Value>): List<Value> {
        return data.sortedWith(compareByDescending { it.vote })
    }
}
