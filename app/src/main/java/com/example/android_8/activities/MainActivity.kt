package com.example.android_8.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_8.R
import com.example.android_8.adapters.ResourceRecyclerViewAdapter
import com.example.android_8.api.PageModel
import com.example.android_8.api.ReqResApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }

    private fun init(){

        val errorTv: TextView = findViewById(R.id.errorTv)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val reqResApi: ReqResApi = retrofit.create(ReqResApi::class.java)
        val call: Call<PageModel> = reqResApi.getResources()
        call.enqueue(object: Callback<PageModel> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<PageModel>, response: Response<PageModel>) {
                if (!response.isSuccessful) {
                    errorTv.text = "Code: " + response.code()
                    return
                }
                response.body()!!.data?.let {
                    recyclerView.adapter = ResourceRecyclerViewAdapter(it)
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                }
//                println("EEEEEEEEEEEEEEEEEEEEEE" + response.body())
            }

            override fun onFailure(call: Call<PageModel>, t: Throwable) {
                errorTv.text = t.message
            }

        })
    }
}