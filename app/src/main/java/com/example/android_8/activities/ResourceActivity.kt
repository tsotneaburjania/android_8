package com.example.android_8.activities

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.android_8.R
import com.example.android_8.adapters.ResourceRecyclerViewAdapter
import com.example.android_8.api.PageModel
import com.example.android_8.api.ReqResApi
import com.example.android_8.api.Resource
import com.example.android_8.api.SingleResourceModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ResourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        val nameTv: TextView = findViewById(R.id.itemNameTv)
        val colorBox: ImageView = findViewById(R.id.itemColorBoxIv)
        val yearTv: TextView = findViewById(R.id.itemYearTv)
        val hexTv: TextView = findViewById(R.id.itemHexTv)
        val pantoneValue: TextView = findViewById(R.id.itemPantoneTv)

        val resourceId = intent.extras?.getInt(ResourceRecyclerViewAdapter.RESOURCE_ID, -1)

        if (resourceId != -1){
            val errorTv: TextView = findViewById(R.id.itemErrorTv)
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val reqResApi: ReqResApi = retrofit.create(ReqResApi::class.java)
            val call: Call<SingleResourceModel> = reqResApi.getResource(resourceId!!)
            call.enqueue(object : Callback<SingleResourceModel> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<SingleResourceModel>, response: Response<SingleResourceModel>) {
                    if (!response.isSuccessful) {
                        errorTv.text = "Code: " + response.code()
                        return
                    }
                    val resource: Resource = response.body()!!.data
                    nameTv.text = resource.name
                    yearTv.text = resource.year.toString()
                    hexTv.text = resource.color
                    pantoneValue.text = resource.pantoneValue
                    colorBox.setBackgroundColor(Color.parseColor(resource.color))

                }

                override fun onFailure(call: Call<SingleResourceModel>, t: Throwable) {
                    errorTv.text = t.message
                }

            })
        }
    }
}