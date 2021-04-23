package com.bts.mufti.testbts.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseCheckList(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
) : Parcelable

@Parcelize
data class ItemsItem(

	@field:SerializedName("itemCompletionStatus")
	val itemCompletionStatus: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null,

	@field:SerializedName("checklistCompletionStatus")
	val checklistCompletionStatus: Boolean? = null
) : Parcelable
