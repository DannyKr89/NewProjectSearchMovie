package com.dk.newprojectsearchmovie.presentation.view.search

import androidx.recyclerview.widget.DiffUtil
import com.dk.newprojectsearchmovie.data.model.search.SearchMovie

class SearchMovieListCallback: DiffUtil.ItemCallback<SearchMovie>() {
    override fun areItemsTheSame(oldItem: SearchMovie, newItem: SearchMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchMovie, newItem: SearchMovie): Boolean {
        return oldItem == newItem
    }
}