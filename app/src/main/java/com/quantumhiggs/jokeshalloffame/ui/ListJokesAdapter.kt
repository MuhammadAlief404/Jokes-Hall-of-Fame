package com.quantumhiggs.jokeshalloffame.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quantumhiggs.jokeshalloffame.R
import com.quantumhiggs.jokeshalloffame.model.Jokes

class ListJokesAdapter (val jokes : List<Jokes>) : RecyclerView.Adapter<ListJokesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_joke, parent, false))

    override fun getItemCount(): Int = jokes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItems(jokes.get(position))

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(joke : Jokes) {

        }

    }
}