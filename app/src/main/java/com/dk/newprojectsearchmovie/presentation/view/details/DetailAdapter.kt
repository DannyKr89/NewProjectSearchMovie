package com.dk.newprojectsearchmovie.presentation.view.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.dk.newprojectsearchmovie.databinding.ItemDetailsListBinding
import com.dk.newprojectsearchmovie.data.model.detailMovie.Actor
import com.dk.newprojectsearchmovie.data.model.detailMovie.Similar
import com.dk.newprojectsearchmovie.presentation.view.details.DetailAdapter.DetailViewHolder

class DetailAdapter(private val list: List<Any>) : RecyclerView.Adapter<DetailViewHolder>() {


    inner class DetailViewHolder(private val binding: ItemDetailsListBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Any) {

            if (item is Actor) {
                with(binding) {
                    name.text = item.name
                    Glide.with(binding.root.context).load(item.image).override(300, 450).fitCenter()
                        .into(image)
                }
            }
            if (item is Similar) {
                with(binding) {
                    name.text = item.title
                    Glide.with(binding.root.context).load(item.image).override(300, 450).fitCenter()
                        .into(image)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding =
            ItemDetailsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(list[position])
    }
}