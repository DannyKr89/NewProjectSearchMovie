package com.dk.newprojectsearchmovie.presentation.view.collections

import androidx.recyclerview.widget.DiffUtil
import com.dk.newprojectsearchmovie.model.imdb.CollectionsMovie

class CollectionsMovieListCallback: DiffUtil.ItemCallback<CollectionsMovie>() {
    override fun areItemsTheSame(oldItem: CollectionsMovie, newItem: CollectionsMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CollectionsMovie, newItem: CollectionsMovie): Boolean {
        return oldItem == newItem
    }
}