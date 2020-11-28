package com.example.aldodokter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aldodokter.entity.DataImage

class HomeFragmentModel : ViewModel() {

    private val listData = MutableLiveData<ArrayList<DataImage>>()

    fun setDataAnimal(){

    }

    fun getDataAnimal() : LiveData<ArrayList<DataImage>> {
        return listData
    }



}