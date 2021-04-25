package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoesViewModel : ViewModel(){

    private val example = ArrayList<Shoe>()

    private val _shoes = MutableLiveData<List<Shoe>>()

    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    private val _eventGoBack = MutableLiveData<Boolean>()
    val eventGoBack: LiveData<Boolean>
        get() = _eventGoBack


    val newShoe = MutableLiveData<Shoe>()


    init {
        example.add(Shoe("Name","0.0", "Company", "Description" ))
        _shoes.value = example
        newShoe.value = Shoe("Empty","Empty","Empty","Empty")
    }

    fun onGoBack() {
        _eventGoBack.value = true
    }

    fun onGoBackComplete() {
        _eventGoBack.value = false
    }

    fun saveShoe(){
        newShoe.value?.let {
            example.add(it)
            _shoes.value = example
        }
        newShoe.value = Shoe("Empty","Empty","Empty","Empty")
        onGoBack()
    }
}