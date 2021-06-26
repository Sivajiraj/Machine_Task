package com.appentus.machinetask.views.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akiniyalocts.pagingrecycler.PagingAdapter
import com.appentus.machinetask.R
import com.appentus.machinetask.model.response.ImageResponseItem
import com.appentus.machinetask.viewholder.MyHolder
import com.squareup.picasso.Picasso
import java.util.*

class ImageAdapter(var detailsList: MutableList<ImageResponseItem>,var mContext: Context) : PagingAdapter() {

    var random: Random

    init {
        random = Random()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val myHolder = holder as MyHolder
        val result = detailsList[position]
        result.let {
            Picasso.with(mContext)
                .load(result.download_url)
                .into(myHolder.imageView);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_adapter_layout, parent, false)
        )
    }

    override fun getPagingLayout(): Int {
        return R.layout.image_adapter_layout
    }

    override fun getPagingItemCount(): Int {
        return detailsList.size
    }
    fun addNewItem(item : Int){
        val lastSize = detailsList.size
        notifyItemInserted(lastSize)
    }
}