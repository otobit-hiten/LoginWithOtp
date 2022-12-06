package com.example.loginwithotp

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback

class Repository {

    fun logindetails(mobile: String): MutableLiveData<Resource<CommomResponse>> {

        val mutableLiveData: MutableLiveData<Resource<CommomResponse>> = MutableLiveData()
        mutableLiveData.postValue(Resource.loading("", null))


        val call: Call<CommomResponse>? = ApiClient.apiService?.login(mobile)

        call?.enqueue(object : Callback<CommomResponse?> {
            override fun onResponse(
                call: Call<CommomResponse?>,
                response: retrofit2.Response<CommomResponse?>
            ) {
                val responseModel: CommomResponse? = response.body()

                if (responseModel != null) {
                    mutableLiveData.postValue(Resource.success(responseModel))
                }
            }

            override fun onFailure(call: Call<CommomResponse?>, t: Throwable) {
                mutableLiveData.postValue(Resource.error("Something went Wrong", null))
            }
        })
        return mutableLiveData
    }
}