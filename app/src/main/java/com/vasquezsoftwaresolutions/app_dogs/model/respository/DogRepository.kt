package com.vasquezsoftwaresolutions.app_dogs.model.respository

import android.util.Log
import androidx.lifecycle.LiveData
import com.vasquezsoftwaresolutions.app_dogs.model.local.dao.DogsDao
import com.vasquezsoftwaresolutions.app_dogs.model.local.entity.DogsImagesEntity
import com.vasquezsoftwaresolutions.app_dogs.model.remote.mapper.fromInternetToBreedEntity
import com.vasquezsoftwaresolutions.app_dogs.model.remote.mapper.fromInternetToImagesEntity
import com.vasquezsoftwaresolutions.app_dogs.model.remote.retrofit.RetrofitClient

class DogRepository(private val dogsDao: DogsDao) {

    private val networkService = RetrofitClient.retrofitInstance()
    val breedListLivedata = dogsDao.getAllBreedList()

    suspend fun fetchBreed() {
        val service = kotlin.runCatching { networkService.getBreedsList() }
        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    dogsDao.insertAllBreedList(fromInternetToBreedEntity(it))
                }

                else -> Log.d("REPO", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }


    //Recibe la raza y realiza la solicitud guardando el elemento en la Base de datos.
    suspend fun fetchDogImages(breed: String) {
        val service = kotlin.runCatching { networkService.getImagesList(breed) }
        service.onSuccess {
            when (it.code()) {
                200 -> it.body()?.let {
                    dogsDao.insertAllImagesList(fromInternetToImagesEntity(it, breed))
                }

                else -> Log.d("REPO-IMG", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }

    // Retorna las imagenes por raza desde la base de datos.
    fun getAllImagesByBreed(breed: String): LiveData<List<DogsImagesEntity>> {
        return dogsDao.getAllDoggiesImages(breed)
    }

    suspend fun updateFavImages(dogsImages: DogsImagesEntity) {
        Log.d("updateFav", " updatefav")
        dogsDao.updateFavImages(dogsImages)
    }

    suspend fun deleteFavImages() {
        Log.d("deleteFav", " updatefav")
        dogsDao.deleteFavImages()
    }
}