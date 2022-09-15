package com.example.alkemymovieschallenge.ui.recyclerViews.tv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.databinding.ItemGridListBinding
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.squareup.picasso.Picasso

class OnTheAirTvViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemGridListBinding.bind(view)
    private val IMAGE_BASE =  "https://image.tmdb.org/t/p/w500"

    fun render(listModel: DomainTvModel){
        binding.tvTitle.text = listModel.title
        binding.tvVoteAverage.text = listModel.voteAverage
       // binding.tvReleaseDate.text = listModel.releaseDate
        Picasso.get().load(IMAGE_BASE + listModel.image).into(binding.ivImage)

    }
}