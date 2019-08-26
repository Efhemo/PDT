package com.efhems.socialvue.ui.listhire

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efhems.socialvue.R
import com.efhems.socialvue.model.Professional
import com.efhems.socialvue.utils.getResId

class ProfListAdapter(val onProfClickLister: OnProfClickLister) : ListAdapter<Professional, ProfListAdapter.ItemViewholder>(DiffCallbackProf()) {

    interface OnProfClickLister{
        fun onClickProf(view: View, prof: Professional)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.prof_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfListAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Professional) = with(itemView) {

            val profTv: TextView = findViewById(R.id.prof_name)
            val msgTv: TextView = findViewById(R.id.msg)
            val rating: RatingBar = findViewById(R.id.rating)
            val state: TextView = findViewById(R.id.state)
            val date: TextView = findViewById(R.id.date)
            val image: ImageView = findViewById(R.id.feature_image)

            profTv.text = item.name
            msgTv.text = item.message
            rating.rating = item.rating.toFloat()
            date.text = "6 days ago"

            if (item.image != "R.drawable.image_1"){
                val id = getResId(item.image, R.drawable::class.java)
                image.setImageResource(id)
            }


            if (item.full_time)  state.text = "Full Time" else state.text = "Part Time"


           /* if(item.full_time){
                state.text = "Full Time"
            }else{
                state.text = "Part Time"

            }*/

            setOnClickListener {
                onProfClickLister.onClickProf(it, item)
            }
        }
    }
}

class DiffCallbackProf : DiffUtil.ItemCallback<Professional>() {
    override fun areItemsTheSame(oldItem: Professional, newItem: Professional): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Professional, newItem: Professional): Boolean {
        return oldItem == newItem

    }
}