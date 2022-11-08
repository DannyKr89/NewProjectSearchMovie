package com.dk.newprojectsearchmovie.view.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.dk.newprojectsearchmovie.databinding.ItemMovieListBinding
import com.dk.newprojectsearchmovie.model.Movie
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieListAdapter(
    private val movieList: List<Movie>, var listener: MainMovieListFragment.SetOnMovieClickListener?
) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    fun removeListener() {
        listener = null
    }


    inner class MovieListViewHolder(private val binding: ItemMovieListBinding) :
        ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {

                Glide.with(binding.root.poster).load(movie.poster).error(movie.posterLocal)
                    .into(poster)

                titleMovie.text = movie.title
                yearMovie.text = movie.year
                ratingMovie.text = movie.rating
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

