package com.example.alkemymovieschallenge.ui.movies.adapters.nowPlaying

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.databinding.ItemGridListBinding
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.utils.Constants
import com.squareup.picasso.Picasso

class NowPlayingMoviesViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemGridListBinding.bind(view)

    fun render(listModel: DomainModel){
        //binding.tvTitle.text = listModel.title
        //binding.tvVoteAverage.text = listModel.voteAverage
       // binding.tvReleaseDate.text = listModel.releaseDate
        Picasso.get().load(Constants.IMAGE_BASE + listModel.image).into(binding.ivImage)

    }
}