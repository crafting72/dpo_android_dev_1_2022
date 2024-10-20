package com.example.m18_permissions.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.m18_permissions.data.SightPhotosDB

class DiffUtilCallback : DiffUtil.ItemCallback<SightPhotosDB>() {
    override fun areItemsTheSame(oldItem: SightPhotosDB, newItem: SightPhotosDB) =
        oldItem.uri == newItem.uri

    override fun areContentsTheSame(oldItem: SightPhotosDB, newItem: SightPhotosDB) = oldItem.equals(newItem)
}