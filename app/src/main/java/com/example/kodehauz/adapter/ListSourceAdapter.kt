package com.example.kodehauz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kodehauz.R
import com.example.kodehauz.`interface`.ItemClickListener
import com.example.kodehauz.adapter.ViewHolder.ListSourceViewHolder
import com.example.kodehauz.model.Website

class ListSourceAdapter(private val context: Context, private val website: Website) :
    RecyclerView.Adapter<ListSourceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSourceViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val item_view = inflater.inflate(R.layout.source, parent, false)
        return ListSourceViewHolder(item_view)


    }

    override fun onBindViewHolder(holder: ListSourceViewHolder, position: Int) {
        holder.source_title.text = website.sources!![position].name

        holder.setItemClickListener(object : ItemClickListener{


            override fun onclick(view: View, position: Int) {

                Toast.makeText(context, "Next Tutorial", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun getItemCount(): Int {

        return website.sources!!.size

    }


}