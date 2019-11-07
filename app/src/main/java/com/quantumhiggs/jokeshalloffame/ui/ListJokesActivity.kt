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

    /**
     * TODO
     * 1. Tampilin hasil dari Btn Add
     * 2. Hilangin hasil dari button add ketika refresh
     * 3. Pindah2 in posisi item di RecView*/

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
            viewModel.setJoke().observe(this, Observer { t ->
                t.let { showData(it) }
            })
        }

        main_swipe_refresh.setOnRefreshListener {
            counter = 1
            main_fab.show()
            main_swipe_refresh.isRefreshing = false
        }

        viewModel.setListJokes().observe(this, Observer { t ->
            t.value.let { showData(it) }
        })
    }

    fun showData(data: List<Value>) {
        data.sortedBy {
            it.vote
        }
        main_rv_jokes.adapter = ListJokesAdapter(data)
    }
}
