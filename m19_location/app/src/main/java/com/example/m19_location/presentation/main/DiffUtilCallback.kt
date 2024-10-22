package com.example.m19_location.presentation.main

import androidx.recyclerview.widget.DiffUtil
import com.example.m19_location.data.room.SightPhotosDB

class DiffUtilCallback : DiffUtil.ItemCallback<SightPhotosDB>() {
    override fun areItemsTheSame(oldItem: SightPhotosDB, newItem: SightPhotosDB) =
        oldItem.uri == newItem.uri

    override fun areContentsTheSame(oldItem: SightPhotosDB, newItem: SightPhotosDB) = oldItem.equals(newItem)
}