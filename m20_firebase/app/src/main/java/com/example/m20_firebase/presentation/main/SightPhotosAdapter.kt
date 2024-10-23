package com.example.m20_firebase.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.m20_firebase.data.room.SightPhotosDB
import com.example.m20_firebase.databinding.SightPhotosBinding

class SightPhotosAdapter : ListAdapter<SightPhotosDB, SightPhotosViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SightPhotosViewHolder(
        SightPhotosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SightPhotosViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding){
            date.text = item?.date ?: ""
            item?.let {
                Glide
                    .with(sightPhoto.context)
                    .load(it.uri)
                    .into(sightPhoto)
            }
        }
    }
}