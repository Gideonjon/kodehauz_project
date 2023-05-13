package com.example.kodehauz.adapter.ViewHolder

import android.content.Context
import android.content.Intent
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kodehauz.ListNews
import com.example.kodehauz.R
import com.example.kodehauz.`interface`.ItemClickListener
import com.example.kodehauz.common.Parse
import com.example.kodehauz.model.Article
import com.example.kodehauz.model.Website
import com.google.gson.internal.bind.util.ISO8601Utils
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.util.Date

class ListNewsAdapter(val articleList: MutableList<Article>, private val context: Context) :
    RecyclerView.Adapter<ListNewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.news_layout, parent, false)


        return ListNewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListNewsViewHolder, position: Int) {

        Picasso.with(context)
            .load(articleList[position].urlToImage)
            .to(holder.article_image)

        if (articleList[position].title!!.length > 65) {

            holder.article_title.text = articleList[position].title!!.substring(0, 65)
        } else {
            holder.article_title.text = articleList[position].title!!

        }
        if (articleList[position].publishedAt != null) {

            var date: Date? = null
            try {
                date = Parse.parse(articleList[position].publishedAt!!)
            } catch (ex: ParseException) {
                ex.printStackTrace()
            }

        }


        holder.setItemClickListener(object : ItemClickListener{
            override fun onclick(view: View, position: Int) {


            }


        })


    }

    override fun getItemCount(): Int {

      return  articleList.size

    }


}


