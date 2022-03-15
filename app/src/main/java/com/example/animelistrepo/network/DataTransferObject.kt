package com.example.animelistrepo.network

import com.example.animelistrepo.database.AnimeDatabase
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

fun NetworkContainer.asDatabaseModel(): Array<AnimeDatabase> {
    return top.map {
        AnimeDatabase (
            animeImage = it.imageUrl,
            tittle = it.title
        )
    }.toTypedArray()
}
@JsonClass(generateAdapter = true)
data class NetworkContainer(val top : List<AnimeProperty>)


@JsonClass(generateAdapter = true)
data class AnimeProperty(
    val title:String,
    @Json(name = "image_url")
    val imageUrl : String
)