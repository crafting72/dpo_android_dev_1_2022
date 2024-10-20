package com.example.m17_recyclerview.data

import com.example.m17_recyclerview.entity.Camera
import com.example.m17_recyclerview.entity.MarsPhotos
import com.example.m17_recyclerview.entity.Photos
import com.example.m17_recyclerview.entity.Rover
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarsPhotosDto(
    @Json(name = "photos") override val photos: List<PhotosDto>
) : MarsPhotos

@JsonClass(generateAdapter = true)
data class PhotosDto (
    @Json(name = "id") override val id: Int,
    @Json(name = "sol") override val sol: Int,
    @Json(name = "camera") override val camera: CameraDto,
    @Json(name = "img_src") override val img_src: String,
    @Json(name = "earth_date") override val earth_date: String,
    @Json(name = "rover") override val rover: RoverDto
) : Photos

@JsonClass(generateAdapter = true)
data class CameraDto (
    @Json(name = "id") override val id: Int,
    @Json(name = "name") override val name: String,
    @Json(name = "rover_id") override val rover_id: Int,
    @Json(name = "full_name") override val full_name: String
) : Camera

@JsonClass(generateAdapter = true)
data class RoverDto (
    @Json(name = "id") override val id: Int,
    @Json(name = "name") override val name: String,
    @Json(name = "landing_date") override val landing_date:String,
    @Json(name = "launch_date") override val launch_date: String,
    @Json(name = "status") override val status: String
) : Rover
