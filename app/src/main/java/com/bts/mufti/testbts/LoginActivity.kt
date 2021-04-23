package com.bts.mufti.testbts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bts.mufti.testbts.data.BodyLogin
import com.bts.mufti.testbts.data.BodyRegister
import com.bts.mufti.testbts.data.ResponseLogRes
import com.bts.mufti.testbts.data.UserPreference
import com.bts.mufti.testbts.databinding.ActivityLoginBinding
import com.bts.mufti.testbts.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Login"

        onListener()
    }

    private fun onListener() {
        binding.btnLogin.setOnClickListener {
            val username = binding.tvLoginUser.text.toString()
            val password = binding.tvLoginPass.text.toString()

            val login = BodyLogin(password, username)
            Log.e("Login_Test", login.toString())

            val retrofit = ApiConfig.provideApiService()

            val client = retrofit.setLogin(login)

            client.enqueue(object : Callback<ResponseLogRes> {

                override fun onResponse(call: Call<ResponseLogRes>, response: Response<ResponseLogRes>) {
                    Log.d("Login_Test", response.body()?.statusCode.toString())
                    Log.d("Login_Test", response.body()?.message.toString())
                    if (response.body()?.statusCode == 2110){
                        Toast.makeText(this@LoginActivity, "Login Complete", Toast.LENGTH_SHORT).show()

                        val userPreference = UserPreference(this@LoginActivity)
                        response.body()?.data?.token?.let { userPreference.setApiKey(it) }

                        val main = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(main)
                        finish()
                    } else{
                        Toast.makeText(this@LoginActivity, "Username or Password wrong", Toast.LENGTH_SHORT).show()
                    }

                }
                override fun onFailure(call: Call<ResponseLogRes>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Auth Fail", Toast.LENGTH_SHORT).show()
                    Log.e("Login_Test", t.message.toString())
                }
            })
        }
        binding.btnRegister.setOnClickListener {
            val register = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(register)
        }

    }


}