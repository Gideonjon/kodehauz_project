package com.example.kodehauz

import android.app.AlertDialog
import android.icu.number.NumberFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kodehauz.`interface`.NewsService
import com.example.kodehauz.adapter.ViewHolder.ListNewsAdapter
import com.example.kodehauz.common.first
import com.example.kodehauz.model.News
import com.flaviofaria.kenburnsview.KenBurnsView
import com.github.florent37.diagonallayout.DiagonalLayout
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListNews : AppCompatActivity() {

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var dialog: AlertDialog
    lateinit var mservice: NewsService
    lateinit var adapter: ListNewsAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var designLayout: DiagonalLayout
    lateinit var recyclerView: RecyclerView
    lateinit var imageview: KenBurnsView
    var webHotUri: String? = ""
    lateinit var author : TextView
    lateinit var title: TextView
    var source = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)
        designLayout = findViewById(R.id.designLayout)
        swipeRefreshLayout = findViewById(R.id.swiper)
        mservice = first.newsService
        dialog = SpotsDialog(this)
        recyclerView = findViewById(R.id.list)
        title = findViewById(R.id.top_title)
        author = findViewById(R.id.top_author)
        imageview = findViewById(R.id.top_image)


        if (intent != null) {

            source = intent.getStringExtra("source").toString()
            if (!source.isEmpty())
                loadNews(source, false)
        }

        designLayout.setOnClickListener {


        }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)


        swipeRefreshLayout.setOnRefreshListener {
            loadNews(source, true)
        }


    }

    private fun loadNews(source: String, isRefreshed: Boolean) {
        if (isRefreshed) {
            dialog.show()
            mservice.getNewsFromSource(first.getNewsApi(source))
                .enqueue(object : Callback<News> {
                    override fun onResponse(call: Call<News>, response: Response<News>) {
                        swipeRefreshLayout.isRefreshing = false
                        Picasso.with(baseContext)
                            .load(response.body()!!.articles!![0].urlToImage)
                            .into(imageview)

                        title.text = response.body()!!.articles!![0].title
                        author.text = response.body()!!.articles!![0].author


                        webHotUri = response.body()!!.articles!![0].url

                        val removeFirstItem = response.body()!!.articles

                        removeFirstItem!!.removeAt(0)


                        adapter = ListNewsAdapter(removeFirstItem, baseContext)
                        adapter.notifyDataSetChanged()


                        recyclerView.adapter = adapter

                    }

                    override fun onFailure(call: Call<News>, t: Throwable) {


                    }


                })


        }

        else{
            swipeRefreshLayout.isRefreshing = true
            dialog.show()
            mservice.getNewsFromSource(first.getNewsApi(source))
                .enqueue(object : Callback<News> {
                    override fun onResponse(call: Call<News>, response: Response<News>) {
                        dialog.dismiss()
                        Picasso.with(baseContext)
                            .load(response.body()!!.articles!![0].urlToImage)
                            .into(imageview)

                        title.text = response.body()!!.articles!![0].title
                        author.text = response.body()!!.articles!![0].author


                        webHotUri = response.body()!!.articles!![0].url

                        val removeFirstItem = response.body()!!.articles

                        removeFirstItem!!.removeAt(0)


                        adapter = ListNewsAdapter(removeFirstItem, baseContext)
                        adapter.notifyDataSetChanged()


                        recyclerView.adapter = adapter

                    }

                    override fun onFailure(call: Call<News>, t: Throwable) {


                    }


                })
        }

    }
}