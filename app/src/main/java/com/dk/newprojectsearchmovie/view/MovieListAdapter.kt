package com.dk.newprojectsearchmovie.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dk.newprojectsearchmovie.databinding.ItemMovieListBinding
import com.dk.newprojectsearchmovie.model.Movie

class MovieListAdapter(private var listener: MainMovieListFragment.SetOnMovieClickListner?) :
    RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private var movieList = listOf<Movie>()

    fun removeListener(){
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
                titleMovie.text = movie.title
                yearMovie.text = movie.year.toString()
                ratingMovie.text = movie.rating.toString()
                movie.poster?.let { poster.setImageResource(it) }
                movieCard.setOnClickListener {
                    listener?.OnMovieClick(movie)
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

