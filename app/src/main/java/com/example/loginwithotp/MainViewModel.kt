package com.example.loginwithotp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel :ViewModel(){

    private val repository = Repository()

            fun postModel() : MutableLiveData<Resource<Response>>{
                return repository.postRepo()
            }
}