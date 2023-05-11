package com.example.kodehauz

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kodehauz.`interface`.NewsService
import com.example.kodehauz.adapter.ListSourceAdapter
import com.example.kodehauz.common.first
import com.example.kodehauz.model.Website
import com.google.gson.Gson
import dmax.dialog.SpotsDialog
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var mService: NewsService
    lateinit var adapter: ListSourceAdapter
    private lateinit var dialog: AlertDialog
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipe)

        recyclerView = findViewById(R.id.recycler_view)
        Paper.init(this)

        mService = first.newsService


        swipeRefreshLayout.setOnRefreshListener {

            loadWebsiteSource(true)

        }

        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        dialog = SpotsDialog(this)
        loadWebsiteSource(false)

    }

    private fun loadWebsiteSource(isRefresh: Boolean) {

        if (isRefresh) {
            val cache = Paper.book().read<String>("cache")
            if (cache !== null && !cache.isBlank() && cache !== "null") {

                val website = Gson().fromJson<Website>(cache, Website::class.java)
                adapter = ListSourceAdapter(baseContext, website)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            } else {
                dialog.show()

                mService.sources.enqueue(object : retrofit2.Callback<Website> {

                    override fun onResponse(call: Call<Website>, response: Response<Website>) {
                        adapter = ListSourceAdapter(baseContext, response.body()!!)
                        adapter.notifyDataSetChanged()
                        recyclerView.adapter = adapter



                        Paper.book().write("cache", Gson().toJson(response.body()))
                        dialog.dismiss()

                    }

                    override fun onFailure(call: Call<Website>, t: Throwable) {
                        Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT).show()

                    }
                })
            }
        } else {
            swipeRefreshLayout.isRefreshing = true
            mService.sources.enqueue(object : retrofit2.Callback<Website> {

                override fun onResponse(call: Call<Website>, response: Response<Website>) {
                    adapter = ListSourceAdapter(baseContext, response.body()!!)
                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = adapter



                    Paper.book().write("cache", Gson().toJson(response.body()))
                    swipeRefreshLayout.isRefreshing = false
                }

                override fun onFailure(call: Call<Website>, t: Throwable) {
                    Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT).show()

                }
            })
        }

    }
}