package com.example.loginwithotp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.loginwithotp.databinding.ActivityOtpBinding

class OtpActivity : AppCompatActivity() {
    lateinit var binding: ActivityOtpBinding
    private var mobile: String = ""
    lateinit var viewModel: OtpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_otp)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(OtpViewModel::class.java)
        mobile = intent.extras?.getString("mobileNumber").toString()
        inti()
    }

    private fun inti() {
        binding.txtMobile.text = mobile
        binding.verifyBtn.setOnClickListener(View.OnClickListener {
            callotpverify()
        })
    }

    private fun callotpverify() {
        Log.d("MobileNumber", binding.edtOtp.text.toString())
        viewModel.otp(mobile, binding.edtOtp.text.toString()).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("Success", "${it.data!!.code.toString()} -- ${it.data} -- ${it.message}")
                    val otpdata = it.data
                    if (otpdata != null) {
                        if (otpdata.code == 200) {
                            Toast.makeText(applicationContext, "Verified:", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(applicationContext, "Failed:", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(applicationContext, "Error:", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    Toast.makeText(applicationContext, "Loading:", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}