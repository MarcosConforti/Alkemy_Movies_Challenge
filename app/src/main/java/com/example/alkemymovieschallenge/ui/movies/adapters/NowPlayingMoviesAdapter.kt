package com.example.alkemymovieschallenge.ui.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.BaseViewHolder
import com.example.alkemymovieschallenge.ui.OnClickListener
import com.example.alkemymovieschallenge.ui.model.UIModel

class NowPlayingMoviesAdapter(
    private var nowPlayingMoviesList: List<UIModel>,
    private var onClickListener: OnClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BaseViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = nowPlayingMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickListener.onItemClicked(item) }
    }

    override fun getItemCount(): Int = nowPlayingMoviesList.size

    fun setNowPlayingMoviesList(newMovieList: List<UIModel>) {
        nowPlayingMoviesList = newMovieList
        notifyDataSetChanged()
    }


}