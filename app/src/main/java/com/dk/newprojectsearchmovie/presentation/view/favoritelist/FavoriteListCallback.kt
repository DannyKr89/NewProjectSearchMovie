package com.dk.newprojectsearchmovie.presentation.view.favoritelist

import androidx.recyclerview.widget.DiffUtil
import com.dk.newprojectsearchmovie.model.imdb.CollectionsMovie

class FavoriteListCallback: DiffUtil.ItemCallback<CollectionsMovie>() {
    override fun areItemsTheSame(oldItem: CollectionsMovie, newItem: CollectionsMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CollectionsMovie, newItem: CollectionsMovie): Boolean {
        return oldItem == newItem
    }
}