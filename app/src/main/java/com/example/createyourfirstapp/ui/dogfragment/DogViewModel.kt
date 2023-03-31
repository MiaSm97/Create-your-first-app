package com.example.createyourfirstapp.ui.dogfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.createyourfirstapp.ui.dogfragment.network.DogService
import com.example.createyourfirstapp.dogdtos.Dog
import com.example.createyourfirstapp.ui.dogfragment.network.DogProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogViewModel(private val dogProvider: DogProvider) : ViewModel() {

    private var dog = MutableLiveData<Dog>()
    val dog2 : LiveData<Dog>
    get() = dog

    private var error = MutableLiveData<String>()
    val error2 : LiveData<String>
    get() = error

    fun dogApi(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
            dog.value = dogProvider.setDetails()
        }catch (e: Exception){
        error.value = e.localizedMessage
        }
        }
    }
}