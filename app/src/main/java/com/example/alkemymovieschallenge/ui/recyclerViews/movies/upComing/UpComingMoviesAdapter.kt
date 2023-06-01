package com.example.alkemymovieschallenge.ui.recyclerViews.movies.upComing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.domain.model.DomainModel
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickMoviesListener

class UpComingMoviesAdapter(
    private var upComingMoviesList: List<DomainModel>,
    private var onClickMoviesListener: OnClickMoviesListener
) : RecyclerView.Adapter<UpComingMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UpComingMoviesViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: UpComingMoviesViewHolder, position: Int) {
        val item = upComingMoviesList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickMoviesListener?.onMoviesClicked(item) }
    }

    override fun getItemCount(): Int = upComingMoviesList.size


    fun setUpComingMoviesList(newMovieList: List<DomainModel>) {
        upComingMoviesList = newMovieList
        notifyDataSetChanged()
    }


}