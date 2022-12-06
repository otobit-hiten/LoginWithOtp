package com.example.loginwithotp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel :ViewModel(){

    private val repository = Repository()

            fun loginCall(mobile:String) : MutableLiveData<Resource<CommomResponse>>{
                return repository.logindetails(mobile)
            }
}