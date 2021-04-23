package com.bts.mufti.testbts.network


import com.bts.mufti.testbts.data.BodyLogin
import com.bts.mufti.testbts.data.BodyRegister
import com.bts.mufti.testbts.data.ResponseCheckList
import com.bts.mufti.testbts.data.ResponseLogRes
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("register")
    fun setRegister(@Body userData: BodyRegister) : Call<ResponseLogRes>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun setLogin(@Body userData: BodyLogin) : Call<ResponseLogRes>

    @Headers("Content-Type: application/json")
    @GET("checklist")
    fun getChecklist(@Header("Authorization") auth: String) : Call<ResponseCheckList>

}