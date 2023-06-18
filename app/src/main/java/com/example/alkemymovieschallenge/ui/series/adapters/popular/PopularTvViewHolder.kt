package com.example.alkemymovieschallenge.ui.series.adapters.popular

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.databinding.ItemGridListBinding
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.utils.Constants
import com.squareup.picasso.Picasso

class PopularTvViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemGridListBinding.bind(view)

    fun render(listModel: UIModel){
        Picasso.get().load(Constants.IMAGE_BASE + listModel.image).into(binding.ivImage)
    }
}