package com.example.alkemymovieschallenge.ui.recyclerViews.tv.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.domain.model.DomainTvModel
import com.example.alkemymovieschallenge.ui.recyclerViews.OnClickTvListener

class PopularTvAdapter(
    private var popularTvList: List<DomainTvModel>,
    private var onClickTvListener: OnClickTvListener
) : RecyclerView.Adapter<PopularTvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PopularTvViewHolder(layoutInflater.inflate(R.layout.item_grid_list, parent, false))
    }

    override fun onBindViewHolder(holder: PopularTvViewHolder, position: Int) {
        val item = popularTvList[position]
        holder.render(item)
        holder.itemView.setOnClickListener { onClickTvListener?.onTvClicked(item) }
    }

    override fun getItemCount(): Int = popularTvList.size

    fun setPopularTvList(newTvList: List<DomainTvModel>) {
        popularTvList = newTvList
        notifyDataSetChanged()
    }


}