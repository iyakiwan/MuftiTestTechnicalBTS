package com.bts.mufti.testbts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bts.mufti.testbts.data.BodyRegister
import com.bts.mufti.testbts.data.ResponseLogRes
import com.bts.mufti.testbts.databinding.ActivityRegisterBinding
import com.bts.mufti.testbts.network.ApiConfig
import com.bts.mufti.testbts.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val retrofit = ApiConfig.provideApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Register"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        onListener()
    }

    private fun onListener() {
        binding.btnRgs.setOnClickListener {
            val email = binding.tvRegisterEmail.text.toString()
            val username = binding.tvRegisterUser.text.toString()
            val password = binding.tvRegisterPass.text.toString()

            val register = BodyRegister(password,username,email)
            Log.e("Register_Test", register.toString())

            val client = retrofit.setRegister(register)

            client.enqueue(object : Callback<ResponseLogRes> {

                override fun onResponse( call: Call<ResponseLogRes>, response: Response<ResponseLogRes>) {
                    Log.d("Register_Test", response.body()?.statusCode.toString())
                    Log.d("Register_Test", response.body()?.message.toString())
                    Toast.makeText(this@RegisterActivity, "Register Complete", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                }
                override fun onFailure(call: Call<ResponseLogRes>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Register Fail", Toast.LENGTH_SHORT).show()
                    Log.e("Register_Test", t.message.toString())
                }
            })
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}