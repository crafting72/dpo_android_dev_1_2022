package com.example.m17_recyclerview.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.m17_recyclerview.R
import com.example.m17_recyclerview.data.MarsPhotosDto
import com.example.m17_recyclerview.data.PhotosDto
import com.example.m17_recyclerview.databinding.MarsPhotoBinding

class MarsPhotosAdapter(
    private val onClick: (PhotosDto) -> Unit
) : RecyclerView.Adapter<MarsPhotosViewHolder>() {
    private var data: MarsPhotosDto = MarsPhotosDto(emptyList())
    fun setData(data: MarsPhotosDto){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotosViewHolder {
        return MarsPhotosViewHolder(
            MarsPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.photos.size

    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val item = data.photos.getOrNull(position)
        val res = holder.itemView.resources
        with(holder.binding){
            camera.text = res.getString(R.string.Camera, item?.camera?.name ?: "")
            sol.text = res.getString(R.string.Sol, item?.sol ?: 0)
            date.text = res.getString(R.string.Date, item?.earth_date ?: "")
            rover.text = res.getString(R.string.Rover, item?.rover?.name ?: "")
            item?.let {
                Glide
                    .with(marsPhoto.context)
                    .load(it.img_src)
                    .into(marsPhoto)
            }
        }
        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(it)
            }
        }
    }

}

class MarsPhotosViewHolder(val binding: MarsPhotoBinding) : RecyclerView.ViewHolder(binding.root)