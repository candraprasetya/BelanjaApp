package com.candrar.belanjaapp2.data.remote.response

import com.google.gson.annotations.SerializedName

data class TestResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("code")
    val code: Int,
    @SerializedName("msg")
    val msg: String
) {
    data class Data(
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("price")
        val price: Int
    )
}