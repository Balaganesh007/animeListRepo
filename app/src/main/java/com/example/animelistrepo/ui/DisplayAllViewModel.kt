package com.example.animelistrepo.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animelistrepo.database.getDatabase
import com.example.animelistrepo.domain.DataModel
import com.example.animelistrepo.repository.AnimeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DisplayAllViewModel(application: Application) : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
    private val database = getDatabase(application)
    private val countryRepository = AnimeRepository(database)

//    private val _relist = MutableLiveData<DataModel>()
//    val relist : LiveData<DataModel>
//        get() = _relist
    init {
        coroutineScope.launch {
            countryRepository.refreshData()
        }
    }
//    _relist = countryRepository.animeData
    val relist = countryRepository.animeData



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}