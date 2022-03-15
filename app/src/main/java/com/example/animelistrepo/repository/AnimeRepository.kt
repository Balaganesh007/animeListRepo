package com.example.animelistrepo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.animelistrepo.database.AnimeDBRoom
import com.example.animelistrepo.database.AnimeDatabase
import com.example.animelistrepo.database.asDomainModel
import com.example.animelistrepo.domain.DataModel
import com.example.animelistrepo.network.ObjectAnimeApi
import com.example.animelistrepo.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeRepository(private val database: AnimeDBRoom) {

    val animeData: LiveData<List<DataModel>> = Transformations.map(database.dataDao.getAnime()) {
        it.asDomainModel()
    }


    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            val rlist = ObjectAnimeApi.retrofitService.getPropertiesAsync().await()
            Log.v("bala",rlist.toString())
            database.dataDao.insertAll(*rlist.asDatabaseModel())
        }
    }
}