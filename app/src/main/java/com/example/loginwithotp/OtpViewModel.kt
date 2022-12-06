package com.example.loginwithotp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OtpViewModel : ViewModel() {

    private var repository = OtpRepository()

            fun otp(mobile:String,otp:String) : MutableLiveData<Resource<LoginResponse>>{
                return repository.otpRepo(mobile,otp)
            }
}