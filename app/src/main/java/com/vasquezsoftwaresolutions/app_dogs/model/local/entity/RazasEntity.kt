package com.vasquezsoftwaresolutions.app_dogs.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *@autor Pablo
 *@create 08-06-2024 19:42
 *@project app_dogs
 *@Version 1.0
 */
@Entity(tableName = "breed_table")
data class RazasEntity(@PrimaryKey val breed: String)
