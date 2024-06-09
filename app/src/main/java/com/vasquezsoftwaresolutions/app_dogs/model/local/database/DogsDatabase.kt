package com.vasquezsoftwaresolutions.app_dogs.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vasquezsoftwaresolutions.app_dogs.model.local.dao.DogsDao
import com.vasquezsoftwaresolutions.app_dogs.model.local.entity.DogsImagesEntity
import com.vasquezsoftwaresolutions.app_dogs.model.local.entity.RazasEntity

/**
 *@autor Pablo
 *@create 08-06-2024 19:58
 *@project app_dogs
 *@Version 1.0
 */
@Database(entities = [RazasEntity::class, DogsImagesEntity::class], version = 1, exportSchema = false)
abstract class DogsDatabase : RoomDatabase() {

    abstract fun dogsDao(): DogsDao
    companion object {

        @Volatile
        private var INSTANCE: DogsDatabase? = null

        fun getDatabase(context: Context): DogsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogsDatabase::class.java,
                    "dogs_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}