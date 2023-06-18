package com.example.alkemymovieschallenge.ui.series.adapters.onTheAir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.ui.series.adapters.OnClickSeriesListener

class OnTheAirTvAdapter(
    private var onTheAirTvList: List<UIModel>,
    private var onClickSeriesListener: OnClickSeriesListener
) : RecyclerView.Adapter<OnTheAirTvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnTheAirTvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OnTheAirTvViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: OnTheAirTvViewHolder, position: Int) {
        val item = onTheAirTvList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickSeriesListener?.onSeriesClicked(item) }
    }

    override fun getItemCount(): Int = onTheAirTvList.size

    fun setOnTheAirTvList(newTvList: List<UIModel>) {
        onTheAirTvList = newTvList
        notifyDataSetChanged()
    }

}