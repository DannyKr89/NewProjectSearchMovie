package com.dk.newprojectsearchmovie.presentation.view.search

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.dk.newprojectsearchmovie.data.model.search.SearchMovie
import com.dk.newprojectsearchmovie.databinding.ItemSearchMovieBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var searchList = listOf<SearchMovie>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<SearchMovie>) {
        searchList = list
        notifyDataSetChanged()
    }


    inner class SearchViewHolder(private val binding: ItemSearchMovieBinding) : ViewHolder(binding.root) {
        fun bind(searchMovie: SearchMovie) {
            with(binding){
                titleSearch.text = searchMovie.title
                yearSearch.text = searchMovie.description
                starsSearch.text = searchMovie.stars
                genresSearch.text = searchMovie.genres
                runtimeSearch.text = searchMovie.runtimeStr
                Glide.with(posterSearch)
                    .load(searchMovie.image)
                    .into(posterSearch)
                ratingSearch.apply {
                    text = searchMovie.imDbRating
                    searchMovie.imDbRating.let {
                        when (it.toDouble()) {
                            in 0.0..4.9 -> setTextColor(Color.RED)
                            in 5.0..6.9 -> setTextColor(Color.YELLOW)
                            in 7.0..10.0 -> setTextColor(Color.GREEN)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchList[position])
    }

    override fun getItemCount() = searchList.size
}