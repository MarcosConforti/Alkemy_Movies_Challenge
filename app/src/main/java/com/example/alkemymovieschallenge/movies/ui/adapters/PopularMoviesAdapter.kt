package com.example.alkemymovieschallenge.movies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.core.ui.BaseViewHolder
import com.example.alkemymovieschallenge.core.ui.UIModel

class PopularMoviesAdapter(
    private var popularMoviesList: List<UIModel>,
    private var onClickListener: OnMoviesClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BaseViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = popularMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickListener.onItemClicked(item) }
    }

    override fun getItemCount(): Int = popularMoviesList.size

    fun setPopularMoviesList(newMovieList: List<UIModel>) {
        popularMoviesList = newMovieList
        notifyDataSetChanged()
    }


}