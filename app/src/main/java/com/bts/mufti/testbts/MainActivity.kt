package com.bts.mufti.testbts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bts.mufti.testbts.adapter.CheckListAdapter
import com.bts.mufti.testbts.data.DataItem
import com.bts.mufti.testbts.data.ResponseCheckList
import com.bts.mufti.testbts.data.UserPreference
import com.bts.mufti.testbts.databinding.ActivityMainBinding
import com.bts.mufti.testbts.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Main"

        val userPreference = UserPreference(this@MainActivity)
//        binding.tvKey.text = userPreference.getUser()

        loadData(userPreference)
    }

    private fun loadData(userPreference: UserPreference) {
        val retrofit = ApiConfig.provideApiService()

        val client = retrofit.getChecklist("Bearer ${userPreference.getUser()}")

        client.enqueue(object : Callback<ResponseCheckList> {

            override fun onResponse(call: Call<ResponseCheckList>, response: Response<ResponseCheckList>) {
                Log.d("Login_Test", response.body()?.statusCode.toString())
                Log.d("Login_Test", response.body()?.message.toString())
//                binding.tvKey.text =  response.body()?.toString()
                binding.rvCheckList.layoutManager = LinearLayoutManager(this@MainActivity)
                val adapter = CheckListAdapter(response.body()?.data as List<DataItem>)
                binding.rvCheckList.adapter = adapter


                /*if (response.body()?.statusCode == 2110){
                    Toast.makeText(this@LoginActivity, "Login Complete", Toast.LENGTH_SHORT).show()

                    val userPreference = UserPreference(this@LoginActivity)
                    response.body()?.data?.token?.let { userPreference.setApiKey(it) }

                    val main = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(main)
                    finish()
                } else{
                    Toast.makeText(this@LoginActivity, "Username or Password wrong", Toast.LENGTH_SHORT).show()
                }*/

            }
            override fun onFailure(call: Call<ResponseCheckList>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Fail Get Data", Toast.LENGTH_SHORT).show()
                Log.e("Login_Test", t.message.toString())
            }
        })
    }


}