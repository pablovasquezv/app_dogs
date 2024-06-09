package com.vasquezsoftwaresolutions.app_dogs.model.remote.api

import com.vasquezsoftwaresolutions.app_dogs.model.remote.response.IImagesResponse
import com.vasquezsoftwaresolutions.app_dogs.model.remote.response.IRazasResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *@autor Pablo
 *@create 08-06-2024 19:37
 *@project app_dogs
 *@Version 1.0
 */
interface DogsApi {
    @GET("breeds/list/all")
    suspend fun getBreedsList(): Response<IRazasResponse>

    @GET("breed/{breed}/images")
    suspend fun getImagesList(@Path("breed") breed: String): Response<IImagesResponse>
}