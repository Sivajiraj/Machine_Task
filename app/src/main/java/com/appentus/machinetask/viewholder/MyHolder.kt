package com.appentus.machinetask.viewholder

import android.view.View
import android.widget.ImageView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.appentus.machinetask.R

class MyHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    var imageView : ImageView = itemView.findViewById(R.id.image)
}