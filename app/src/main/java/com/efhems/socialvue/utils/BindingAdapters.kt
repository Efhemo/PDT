package com.efhems.socialvue.utils

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("isProf")
fun isProf(llayout: LinearLayoutCompat, b: Boolean): Unit {

    /*if(b){
        llayout.background = Drawable.createFromResourceStream()
    }*/

}

/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

/*@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: androidx.recyclerview.widget.RecyclerView, data: List<Model>?) {
    val adapter = recyclerView.adapter as HireListAdapter
    adapter.submitList(data) {
        // scroll the list to the top after the diffs are calculated and posted
        recyclerView.scrollToPosition(0)
    }
}*/

@BindingAdapter("image")
fun setImage(view: androidx.appcompat.widget.AppCompatImageView, drawable: String): Unit {

    val x = drawable.toInt()
    view.setImageResource(x)
}