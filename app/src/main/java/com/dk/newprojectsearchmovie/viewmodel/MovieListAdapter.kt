package com.dk.newprojectsearchmovie.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dk.newprojectsearchmovie.databinding.ItemMovieListBinding
import com.dk.newprojectsearchmovie.model.Movie

class MovieListAdapter() :
    RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
    private var movieList: List<Movie> = listOf<Movie>()

    fun setMovieList(list: List<Movie>){
        movieList = list
    }


    class MovieListViewHolder(private val binding: ItemMovieListBinding) :
        ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                titleMovie.text = movie.title
                yearMovie.text = movie.year.toString()
                ratingMovie.text = movie.rating.toString()
                movie.poster?.let { poster.setImageResource(it) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}