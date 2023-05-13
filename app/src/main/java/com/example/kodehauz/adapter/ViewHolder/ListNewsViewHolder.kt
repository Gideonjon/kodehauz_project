package com.example.kodehauz.adapter.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kodehauz.R
import com.example.kodehauz.`interface`.ItemClickListener

class ListNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private lateinit var itemClickListener: ItemClickListener

    var article_title = itemView.findViewById<TextView>(R.id.article_title)
    var article_time = itemView.findViewById<TextView>(R.id.article_time)
    var article_image = itemView.findViewById<TextView>(R.id.article_image)


    init {
        itemView.setOnClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {

        this.itemClickListener = itemClickListener

    }


    override fun onClick(v: View) {
        itemClickListener.onclick(v, adapterPosition)

    }


}