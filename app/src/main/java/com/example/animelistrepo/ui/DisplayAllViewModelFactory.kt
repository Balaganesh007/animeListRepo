package com.example.animelistrepo.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DisplayAllViewModelFactory (val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisplayAllViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DisplayAllViewModel(application) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}