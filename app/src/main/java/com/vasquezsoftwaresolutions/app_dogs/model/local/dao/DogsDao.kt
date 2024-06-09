package com.vasquezsoftwaresolutions.app_dogs.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vasquezsoftwaresolutions.app_dogs.model.local.entity.DogsImagesEntity
import com.vasquezsoftwaresolutions.app_dogs.model.local.entity.RazasEntity

/**
 *@autor Pablo
 *@create 08-06-2024 19:45
 *@project app_dogs
 *@Version 1.0
 */

@Dao
interface DogsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBreedList(listBreed: List<RazasEntity>)

    @Query("SELECT * FROM breed_table ORDER BY breed ASC")
    fun getAllBreedList(): LiveData<List<RazasEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllImagesList(dogsImages: List<DogsImagesEntity>)

    @Update
    suspend fun updateFavImages(dogsImages: DogsImagesEntity)

    @Query("UPDATE images_table SET fav = 0 WHERE fav = 1")
    suspend fun deleteFavImages()

    @Query("SELECT * FROM images_table WHERE breed = :breed")
    fun getAllDoggiesImages(breed: String): LiveData<List<DogsImagesEntity>>

    // Funcion que trae todos los favoritos
    @Query("SELECT * FROM images_table WHERE fav = 1")
    fun getAllFavImages(): LiveData<List<DogsImagesEntity>>
}