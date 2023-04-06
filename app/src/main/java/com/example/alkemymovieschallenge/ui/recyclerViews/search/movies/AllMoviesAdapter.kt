package com.example.alkemymovieschallenge.ui.recyclerViews.search.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickMoviesListener

class AllMoviesAdapter(
    private var allMoviesList: List<DomainModel>,
    private var onClickMoviesListener: OnClickMoviesListener
) : RecyclerView.Adapter<AllMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AllMoviesViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: AllMoviesViewHolder, position: Int) {
        val item = allMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickMoviesListener?.onMoviesClicked(item) }
    }

    override fun getItemCount(): Int = allMoviesList.size

    fun setAllMoviesList(newMovieList: List<DomainModel>) {
        allMoviesList = newMovieList
        notifyDataSetChanged()
    }


}