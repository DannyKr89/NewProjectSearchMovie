package com.dk.newprojectsearchmovie.presentation.view.movielist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.dk.newprojectsearchmovie.databinding.ItemMovieListBinding
import com.dk.newprojectsearchmovie.model.imdb.Movie

class MovieListAdapter(
    var listener: MainMovieListFragment.SetOnMovieClickListener?
) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private var movieList = listOf<Movie>()

    fun removeListener() {
        listener = null
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(list: List<Movie>){
        movieList = list
        notifyDataSetChanged()
    }


    inner class MovieListViewHolder(private val binding: ItemMovieListBinding) :
        ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {

                Glide.with(poster).load(movie.image)
                    .into(poster)

                titleMovie.text = movie.title
                yearMovie.text = movie.year
                ratingMovie.text = movie.imDbRating
                movieCard.setOnClickListener {
                    listener?.onMovieClick(movie)
                }
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

    override fun getItemCount(): Int = movieList.size


}

