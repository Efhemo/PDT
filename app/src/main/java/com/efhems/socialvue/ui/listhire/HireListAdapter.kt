package com.efhems.socialvue.ui.listhire

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efhems.socialvue.R
import com.efhems.socialvue.model.Model
import com.efhems.socialvue.utils.getResId


class HireListAdapter(val onHireClickLister: OnHireClickLister) : ListAdapter<Model, HireListAdapter.ItemViewholder>(DiffCallback()) {

    interface OnHireClickLister{
        fun onClickHire(view: View, model: Model)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.category_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Model) = with(itemView) {

            val img: AppCompatImageView = findViewById(R.id.prof_img)
            val tv: AppCompatTextView = findViewById(R.id.prof_tv)

            val resID = getResId(item.icon, R.drawable::class.java)
            tv.text = item.category
            img.setImageResource(resID)
            setOnClickListener {
                onHireClickLister.onClickHire(it, item)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Model>() {
    override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem.id == newItem.id


    }

    override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
        return oldItem == newItem

    }
}