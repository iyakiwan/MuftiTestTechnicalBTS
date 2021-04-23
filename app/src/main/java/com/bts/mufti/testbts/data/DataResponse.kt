package com.bts.mufti.testbts.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseLogRes(

	@field:SerializedName("data")
	val data: DataToken? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
) : Parcelable

@Parcelize
data class DataToken(
	@field:SerializedName("token")
	val token: String? = null
) : Parcelable

