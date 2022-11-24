package com.dk.newprojectsearchmovie.presentation.view.favoritelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.dk.newprojectsearchmovie.databinding.ItemMovieListBinding
import com.dk.newprojectsearchmovie.model.imdb.CollectionsMovie

class FavoriteListAdapter(
) : ListAdapter<CollectionsMovie,FavoriteListAdapter.MovieListViewHolder>(FavoriteListCallback()) {


    var listener: ((CollectionsMovie) -> Unit)? = null

    inner class MovieListViewHolder(private val binding: ItemMovieListBinding) :
        ViewHolder(binding.root) {

        fun bind(movie: CollectionsMovie) {
            with(binding) {

                Glide.with(poster).load(movie.image)
                    .into(poster)

                titleMovie.text = movie.title
                yearMovie.text = movie.year
                ratingMovie.text = movie.imDbRating
                movieCard.setOnClickListener {
                    listener?.invoke(movie)
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
        holder.bind(getItem(position))
    }



}

