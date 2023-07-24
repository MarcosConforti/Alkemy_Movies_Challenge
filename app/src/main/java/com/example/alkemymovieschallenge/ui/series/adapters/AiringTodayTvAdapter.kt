package com.example.alkemymovieschallenge.ui.series.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.BaseViewHolder
import com.example.alkemymovieschallenge.ui.model.UIModel
import com.example.alkemymovieschallenge.ui.series.OnSeriesClickListener

class AiringTodayTvAdapter(
    private var airingTodayTvList: List<UIModel>,
    private var onClickListener: OnSeriesClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BaseViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = airingTodayTvList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickListener.onItemClicked(item) }
    }

    override fun getItemCount(): Int = airingTodayTvList.size

    fun setAiringTodayTvList(newTvList: List<UIModel>) {
        airingTodayTvList = newTvList
        notifyDataSetChanged()
    }

}