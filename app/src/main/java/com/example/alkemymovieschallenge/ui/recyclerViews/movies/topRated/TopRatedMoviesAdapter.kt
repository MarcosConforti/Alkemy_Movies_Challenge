package com.example.alkemymovieschallenge.ui.recyclerViews.movies.topRated

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickMoviesListener

class TopRatedMoviesAdapter(
    private var topRatedMoviesList: List<DomainModel>,
    private var onClickMoviesListener: OnClickMoviesListener
) : RecyclerView.Adapter<TopRatedMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TopRatedMoviesViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {
        val item = topRatedMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickMoviesListener.onMoviesClicked(item) }
    }

    override fun getItemCount(): Int = topRatedMoviesList.size

    //override fun getFilter(): Filter = filter

    fun setTopRatedMoviesList(newMovieList: List<DomainModel>) {
        topRatedMoviesList = newMovieList
        notifyDataSetChanged()
    }


}