package com.example.android_8.adapters

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android_8.R
import com.example.android_8.activities.ResourceActivity
import com.example.android_8.api.Resource

class ResourceRecyclerViewAdapter(private var list: List<Resource>) :
    RecyclerView.Adapter<ResourceRecyclerViewAdapter.ResourceViewHolder>() {

    companion object {
        const val RESOURCE_ID = "RESOURCE_ID"
    }

    class ResourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var nameTv: TextView = itemView.findViewById(R.id.nameTv)
        private var colorBox: ImageView = itemView.findViewById(R.id.colorBoxIv)
        private var yearTv: TextView = itemView.findViewById(R.id.yearTv)
        private var hexTv: TextView = itemView.findViewById(R.id.hexTv)
        private var pantoneValue: TextView = itemView.findViewById(R.id.pantoneTv)

        init {
            itemView.setOnClickListener(this)
        }

        private lateinit var resource: Resource

        fun onBind(resource: Resource){
            this.resource = resource

            nameTv.text = resource.name
            colorBox.setBackgroundColor(Color.parseColor(resource.color))
            yearTv.text = resource.year.toString()
            hexTv.text = resource.color
            pantoneValue.text = resource.pantoneValue
        }

        override fun onClick(target: View?) {
            val intent = Intent(itemView.context, ResourceActivity::class.java)
            intent.putExtra(RESOURCE_ID, resource.id)
            itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_resource, parent, false)

        return ResourceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}