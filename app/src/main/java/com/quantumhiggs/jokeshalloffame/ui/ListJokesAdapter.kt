package com.quantumhiggs.jokeshalloffame.ui

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quantumhiggs.jokeshalloffame.R
import com.quantumhiggs.jokeshalloffame.model.Value
import kotlinx.android.synthetic.main.item_list_joke.view.*

class ListJokesAdapter(private val jokes: List<Value>, private val upClickListener: (Value) -> Unit) :
    RecyclerView.Adapter<ListJokesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_joke, parent, false))

    override fun getItemCount(): Int = jokes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(jokes[position], position, upClickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(joke: Value, position: Int, upClickListener: (Value) -> Unit) {
            itemView.item_list_joke_number.text = joke.vote.toString()
            itemView.item_list_joke_value.text = joke.joke

            if (position == 0) {
                itemView.item_list_joke_top_text.visibility = View.VISIBLE
                itemView.item_list_joke_btn_up.visibility = View.GONE
            } else {
                itemView.item_list_joke_top_text.visibility = View.INVISIBLE
                itemView.item_list_joke_btn_up.visibility = View.VISIBLE
            }

            itemView.setOnClickListener {
                val alertBuilder: AlertDialog.Builder? = itemView.context.let {
                    AlertDialog.Builder(it)
                }
                alertBuilder?.setMessage(joke.joke)?.setTitle("The Joke is")
                alertBuilder?.create()?.show()
            }

            itemView.item_list_joke_btn_up.setOnClickListener {
                joke.pos = position
                upClickListener(joke)
            }
        }

    }
}