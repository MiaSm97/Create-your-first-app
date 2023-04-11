package com.example.createyourfirstapp.ui.dogfragment

import com.example.createyourfirstapp.dogdtos.Dog

sealed class GetDogs {
    data class SetDogsBreed(val breed : String) : GetDogs()
}

sealed class DogResults{
    data class Results(val dog : Dog) : DogResults()
    data class Error(val errors : String) : DogResults()
    object CheckPreferences : DogResults()
}