package com.example.loginwithotp

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback

class Repository() {

    fun postRepo():MutableLiveData<Resource<Response>>{

        val mutableLiveData :MutableLiveData<Resource<Response>> = MutableLiveData()
        mutableLiveData.postValue(Resource.loading("",null))


        val call : Call<Response>? = ApiClient.apiService?.post(1,1,"HELLO", "POST REQUEST")

        call?.enqueue(object : Callback<Response?> {
            override fun onResponse(
                call: Call<Response?>,
                response: retrofit2.Response<Response?>
            )
            {
                val responseModel : Response? =response.body()

                if (responseModel != null) {
                    mutableLiveData.postValue(Resource.success(responseModel))
                }
            }

            override fun onFailure(call: Call<Response?>, t: Throwable) {
                mutableLiveData.postValue(Resource.error(t.toString(), null))
            }
        })
        return mutableLiveData
    }
}