package com.candrar.belanjaapp2.data.remote

import com.candrar.belanjaapp2.BuildConfig
import com.candrar.belanjaapp2.data.model.Product
import com.candrar.belanjaapp2.data.remote.response.ProductResponse
import com.candrar.belanjaapp2.data.remote.response.ProductsResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object BelanjaApi {

    fun create(): Api {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
        return retrofit.create(Api::class.java)
    }

    interface Api {
        @GET("/products")
        fun getProducts(): Call<ProductsResponse>

        @POST("/products")
        fun saveProduct(@Body product: Product): Call<ProductResponse>

        @DELETE("/products/{id}")
        fun deleteProduct(@Path("id") id: Int): Call<JsonObject>

        @PUT("/products/{id}")
        fun updateProuct(@Path("id") id: Int, @Body product: Product): Call<ProductResponse>
    }
}