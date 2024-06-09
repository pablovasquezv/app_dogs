package com.vasquezsoftwaresolutions.app_dogs.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vasquezsoftwaresolutions.app_dogs.model.local.database.DogsDatabase
import com.vasquezsoftwaresolutions.app_dogs.model.local.entity.DogsImagesEntity
import com.vasquezsoftwaresolutions.app_dogs.model.local.entity.RazasEntity
import com.vasquezsoftwaresolutions.app_dogs.model.respository.DogRepository
import kotlinx.coroutines.launch

/**
 *@autor Pablo
 *@create 08-06-2024 20:05
 *@project app_dogs
 *@Version 1.0
 */
class DogViewModel(application: Application):AndroidViewModel(application) {

    private val respository : DogRepository

    init {
        val db= DogsDatabase.getDatabase(application)
        val dogDao= db.dogsDao()
        respository= DogRepository(dogDao)

        viewModelScope.launch {
             respository
        }
    }


    //Todas las razas de perro desde la DataBase
    fun getBreedList(): LiveData<List<RazasEntity>> = respository.breedListLivedata
    // Para las images
    private var breedSelected : String = ""

    fun getImagesByBreedFromInternet(breed: String) = viewModelScope.launch {
        breedSelected = breed
        respository.fetchDogImages(breed)
    }

    fun getImages(): LiveData<List<DogsImagesEntity>> = respository.getAllImagesByBreed(breedSelected)


    fun updateFav(dogsImages: DogsImagesEntity) = viewModelScope.launch {
        Log.d("repoFav", " repo fav")
        respository.updateFavImages(dogsImages)
    }
    fun deleteallFav() {
        viewModelScope.launch {
            Log.d("repoFav", " repo fav")
            respository.deleteFavImages()
        }
    }
}