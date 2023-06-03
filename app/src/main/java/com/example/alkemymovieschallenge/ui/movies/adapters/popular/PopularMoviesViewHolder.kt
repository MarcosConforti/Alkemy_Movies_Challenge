package com.example.alkemymovieschallenge.ui.movies.adapters.popular

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.databinding.ItemGridListBinding
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.utils.Constants
import com.squareup.picasso.Picasso

class PopularMoviesViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemGridListBinding.bind(view)

    fun render(listModel: DomainModel){
        Picasso.get().load(Constants.IMAGE_BASE + listModel.image).into(binding.ivImage)
    }
}