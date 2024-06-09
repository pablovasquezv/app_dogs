package com.vasquezsoftwaresolutions.app_dogs.model.remote.retrofit

import com.vasquezsoftwaresolutions.app_dogs.model.remote.api.DogsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *@autor Pablo
 *@create 08-06-2024 19:41
 *@project app_dogs
 *@Version 1.0
 */
class RetrofitClient {
    companion object {

        private const val BASE_URL = "https://dog.ceo/api/"

        lateinit var retrofitInstance: Retrofit
        fun retrofitInstance(): DogsApi {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            return retrofit.create(DogsApi::class.java)
        }
    }
}