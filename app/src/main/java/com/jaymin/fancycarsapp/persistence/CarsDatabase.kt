package com.jaymin.fancycarsapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Car::class], version = 1)
abstract class CarsDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao

    companion object {

        private var instance: CarsDatabase? = null

        fun getInstance(context: Context): CarsDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context, CarsDatabase::class.java, "cars.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance as CarsDatabase
        }

        fun destroyInstance() {
            instance = null
        }
    }
}