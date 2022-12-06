package com.example.loginwithotp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.loginwithotp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(MainViewModel::class.java)
        binding.nextBtn.setOnClickListener(View.OnClickListener {
            if (binding.edtPhone.text.isEmpty()) {
                Toast.makeText(applicationContext, "Enter mobile number", Toast.LENGTH_SHORT).show()
            } else if (binding.edtPhone.length() != 10) {
                Toast.makeText(applicationContext, "Enter Valid Number", Toast.LENGTH_SHORT).show()
            } else {
                callLogin()
            }
        })
    }

    private fun callLogin() {
        Log.d("MobileNumber", binding.edtPhone.text.toString())
        viewModel.loginCall(binding.edtPhone.text.toString().trim()).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("Success", "${it.data!!.code.toString()} -- ${it.data} -- ${it.message}")
                    val logindata = it.data
                    if (logindata != null) {
                        if (logindata.code == 200) {
                            val intent = Intent(this, OtpActivity::class.java)
                            intent.putExtra("mobileNumber", binding.edtPhone.text.toString())
                            startActivity(intent)
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