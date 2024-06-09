package com.vasquezsoftwaresolutions.app_dogs.model.remote.mapper

import com.vasquezsoftwaresolutions.app_dogs.model.local.entity.DogsImagesEntity
import com.vasquezsoftwaresolutions.app_dogs.model.local.entity.RazasEntity
import com.vasquezsoftwaresolutions.app_dogs.model.remote.response.IImagesResponse
import com.vasquezsoftwaresolutions.app_dogs.model.remote.response.IRazasResponse

/**
 *@autor Pablo
 *@create 08-06-2024 19:42
 *@project app_dogs
 *@Version 1.0
 */
fun fromInternetToBreedEntity(iRazas: IRazasResponse): List<RazasEntity> {
    val breedNames = iRazas.message.keys
    return breedNames.map{breedNames->

        RazasEntity(breed = breedNames) }
}

fun fromInternetToImagesEntity(iImages: IImagesResponse, breed: String): List<DogsImagesEntity> {
    val imageName= iImages.message
    return imageName.map {imageName ->
        DogsImagesEntity(imageUrl = imageName, breed = breed) }
}