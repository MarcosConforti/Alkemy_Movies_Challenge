package com.example.alkemymovieschallenge.ui.recyclerViews.search.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.BaseViewHolder
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.ui.movies.OnClickListener

class AllMoviesAdapter(
    private var allMoviesList: List<UIModel>,
    private var onClickMoviesListener: OnClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BaseViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = allMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickMoviesListener?.onItemClicked(item) }
    }

    override fun getItemCount(): Int = allMoviesList.size

    fun setAllMoviesList(newMovieList: List<UIModel>) {
        allMoviesList = newMovieList
        notifyDataSetChanged()
    }
}