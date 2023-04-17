package com.example.createyourfirstapp.ui.dogfragment

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.createyourfirstapp.ui.dogfragment.network.DogProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class DogViewModel(private val dogProvider: DogProvider, preferences: SharedPreferences) :
    ViewModel() {

    val dog = MutableSharedFlow<DogResults>()

    init {
        checkPreferences(preferences)
    }

    private fun checkPreferences(preferences: SharedPreferences) {
        val firstTimePreferences = preferences.getBoolean(KEY_PREFERENCES, true)
        if (firstTimePreferences) {
            preferences.edit().putBoolean(KEY_PREFERENCES, false).apply()
            viewModelScope.launch {
                dog.emit(DogResults.CheckPreferences)
            }

        }
    }

    private fun dogApi() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                dog.emit(DogResults.Results(dogProvider.setDetails()))
            } catch (e: Exception) {
                e.localizedMessage?.let { DogResults.Error(it) }?.let { dog.emit(it) }
            }
        }
    }

    fun getDogApi(setDog: GetDogs) {
        when (setDog) {
            is GetDogs.SetDogsBreed -> dogApi()
        }
    }

}

const val KEY_PREFERENCES = "check_preferences"