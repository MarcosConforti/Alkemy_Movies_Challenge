package com.example.alkemymovieschallenge.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkemymovieschallenge.R
import com.example.alkemymovieschallenge.ui.BaseViewHolder
import com.example.alkemymovieschallenge.ui.model.UIModel

class AlternativeTitleAdapter(
    private var alternativeTitleList: List<UIModel>
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BaseViewHolder(
            layoutInflater.inflate(
                R.layout.item_grid_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = alternativeTitleList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = alternativeTitleList.size

    fun setAlternativeTitleList(newList : List<UIModel>){
        alternativeTitleList = newList
        notifyDataSetChanged()
    }
}