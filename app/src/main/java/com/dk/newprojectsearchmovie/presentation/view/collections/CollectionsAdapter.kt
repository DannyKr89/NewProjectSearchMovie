package com.dk.newprojectsearchmovie.presentation.view.collections

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dk.newprojectsearchmovie.databinding.ItemSearchMovieBinding
import com.dk.newprojectsearchmovie.model.imdb.CollectionsMovie

class CollectionsAdapter : ListAdapter<CollectionsMovie, CollectionsAdapter.CollectionsViewHolder>(
    CollectionsMovieListCallback()
) {

    var listener: ((CollectionsMovie) -> Unit)? = null

    inner class CollectionsViewHolder(private val binding: ItemSearchMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(collectionsMovie: CollectionsMovie) {
            with(binding){
                titleSearch.text = collectionsMovie.title
                yearSearch.text = collectionsMovie.year
                starsSearch.text = collectionsMovie.crew
                genresSearch.text = ""
                runtimeSearch.text = ""
                Glide.with(posterSearch)
                    .load(collectionsMovie.image)
                    .into(posterSearch)
                ratingSearch.apply {
                    text = collectionsMovie.imDbRating
                    collectionsMovie.imDbRating.let {
                        when (it.toDouble()) {
                            in 0.0..4.9 -> setTextColor(Color.RED)
                            in 5.0..6.9 -> setTextColor(Color.YELLOW)
                            in 7.0..10.0 -> setTextColor(Color.GREEN)
                        }
                    }
                }
                item.setOnClickListener {
                    listener?.invoke(collectionsMovie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val binding = ItemSearchMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CollectionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}