package com.example.homework_013.model


import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


/*
import com.google.gson.annotations.SerializedName

data class NewsItem(
    val id: Int,
    val title: String,
    @SerializedName("img-url")
    val imgUrl: String,
    val description: String,
    val date: String,
    val images: Any?,
    @SerializedName("created_at")
    val createdAt: Any?,
    @SerializedName("updated_at")
    val updatedAt: Any?,
    @SerializedName("deleted_at")
    val deletedAt: Any?
)*/

@JsonClass(generateAdapter = true)
data class NewsItem(
    val id: Int,
    val title: String,
    @Json(name = "img-url")
    val imgUrl: String,
    val description: String,
    val date: String,
    val images: Any?,
    @Json(name = "created_at")
    val createdAt: Any?,
    @Json(name = "updated_at")
    val updatedAt: Any?,
    @Json(name = "deleted_at")
    val deletedAt: Any?
)
