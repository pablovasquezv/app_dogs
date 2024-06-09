package com.vasquezsoftwaresolutions.app_dogs.model.local.entity

import androidx.room.Entity

/**
 *@autor Pablo
 *@create 08-06-2024 19:44
 *@project app_dogs
 *@Version 1.0
 */
@Entity(tableName = "images_table", primaryKeys = ["breed", "imageUrl"])
data class DogsImagesEntity(
    val imageUrl: String,
    val breed: String,
    var fav: Boolean = false
)
