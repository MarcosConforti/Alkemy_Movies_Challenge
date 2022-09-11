package com.example.alkemymovieschallenge.ui.recyclerViews.movies.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickMoviesListener

class PopularMoviesAdapter(
    private var popularMoviesList: List<DomainModel>,
    private var onClickMoviesListener: OnClickMoviesListener
) : RecyclerView.Adapter<PopularMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PopularMoviesViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val item = popularMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickMoviesListener?.onMoviesClicked(item) }
    }

    override fun getItemCount(): Int = popularMoviesList.size

    //override fun getFilter(): Filter = filter

    fun setPopularMoviesList(newMovieList: List<DomainModel>) {
        popularMoviesList = newMovieList
        notifyDataSetChanged()
    }


}