package com.example.animelistrepo.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animelistrepo.domain.DataModel


@Entity
data class AnimeDatabase constructor(
    @PrimaryKey
    val tittle: String,
    val animeImage : String
)

fun List<AnimeDatabase>.asDomainModel(): List<DataModel> {
    return map {
        DataModel(
            tittle = it.tittle,
            animeImage = it.animeImage
        )
    }
}