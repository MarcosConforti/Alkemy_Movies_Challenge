package com.example.alkemymovieschallenge.ui.adapters.tv.airing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.ui.adapters.OnClickTvListener

class AiringTodayTvAdapter(
    private var airingTodayTvList: List<DomainTvModel>,
    private var onClickTvListener: OnClickTvListener
) : RecyclerView.Adapter<AiringTodayTvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AiringTodayTvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AiringTodayTvViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: AiringTodayTvViewHolder, position: Int) {
        val item = airingTodayTvList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickTvListener?.onTvClicked(item) }
    }

    override fun getItemCount(): Int = airingTodayTvList.size

    fun setAiringTodayTvList(newTvList: List<DomainTvModel>) {
        airingTodayTvList = newTvList
        notifyDataSetChanged()
    }


}