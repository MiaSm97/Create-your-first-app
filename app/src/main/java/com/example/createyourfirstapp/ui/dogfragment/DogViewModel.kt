package com.example.createyourfirstapp.ui.dogfragment

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.createyourfirstapp.ui.dogfragment.network.DogService
import com.example.createyourfirstapp.dogdtos.Dog
import com.example.createyourfirstapp.ui.dogfragment.network.DogProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogViewModel(private val dogProvider: DogProvider, preferences: SharedPreferences) : ViewModel() {

    private var dog = MutableLiveData<DogResults>()
    val dog2 : LiveData<DogResults>
    get() = dog

    init {
        checkPreferences(preferences)
    }

    private fun checkPreferences(preferences: SharedPreferences){
        val firstTimePreferences = preferences.getBoolean(KEY_PREFERENCES, true)
        if(firstTimePreferences){
            preferences.edit().putBoolean(KEY_PREFERENCES, false).apply()
            dog.value = DogResults.CheckPreferences
        }
    }
    private fun dogApi(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
            dog.value = DogResults.Results(dogProvider.setDetails())
        }catch (e: Exception){
            dog.value = e.localizedMessage?.let { DogResults.Error(it) }
        }
        }
    }

    fun getDogApi(setDog: GetDogs){
        when(setDog){
            is GetDogs.SetDogsBreed -> dogApi()
        }
    }

}

const val KEY_PREFERENCES = "check_preferences"