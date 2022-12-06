package com.example.loginwithotp

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpRepository {

    fun otpRepo(mobile:String,otp:String): MutableLiveData<Resource<LoginResponse>> {
        val mutableLiveData: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
        mutableLiveData.postValue(Resource.loading("",null))
        val call : Call<LoginResponse>? =ApiClient.apiService?.verify(mobile,otp)

        call?.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                val responseModel : LoginResponse? = response.body()

                if(responseModel != null){
                    mutableLiveData.postValue(Resource.success(responseModel))
                }
            }
            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                mutableLiveData.postValue( Resource.error("", null))
            }
        })
        return mutableLiveData
    }
}