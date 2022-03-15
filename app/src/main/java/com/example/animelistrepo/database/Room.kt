package com.example.animelistrepo.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataDao {
    @Query("select * from animedatabase")
    fun getAnime(): LiveData<List<AnimeDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg countries: AnimeDatabase)
}

@Database(entities = [AnimeDatabase::class], version = 1, exportSchema = false)
abstract class AnimeDBRoom : RoomDatabase() {
    abstract val dataDao: DataDao
}

    private lateinit var INSTANCE: AnimeDBRoom

    fun getDatabase(context: Context): AnimeDBRoom {
        synchronized(AnimeDBRoom::class.java) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AnimeDBRoom::class.java,
                    "Anime").build()
            }
        }
        return INSTANCE
    }