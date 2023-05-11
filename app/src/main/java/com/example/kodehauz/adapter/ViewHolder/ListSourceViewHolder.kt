package com.example.kodehauz.adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kodehauz.R
import com.example.kodehauz.`interface`.ItemClickListener

class ListSourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {


    private lateinit var itemClickListener: ItemClickListener
    var source_title = itemView.findViewById<TextView>(R.id.news_title)


    fun setItemClickListener(itemClickListener: ItemClickListener) {

        this.itemClickListener = itemClickListener

    }


    override fun onClick(v: View?) {
        itemClickListener.onclick(v!!, adapterPosition)

    }

}