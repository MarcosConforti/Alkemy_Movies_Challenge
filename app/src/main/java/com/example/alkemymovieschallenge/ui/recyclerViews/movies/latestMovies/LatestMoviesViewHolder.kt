package com.example.alkemymovieschallenge.ui.recyclerViews.movies.latestMovies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.databinding.ItemGridListBinding
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.squareup.picasso.Picasso

class LatestMoviesViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemGridListBinding.bind(view)
    private val IMAGE_BASE =  "https://image.tmdb.org/t/p/w500"

    fun render(listModel: DomainModel){
        binding.tvTitle.text = listModel.title
        binding.tvVoteAverage.text = listModel.voteAverage
       // binding.tvReleaseDate.text = listModel.releaseDate
        Picasso.get().load(IMAGE_BASE + listModel.image).into(binding.ivImage)

    }
}