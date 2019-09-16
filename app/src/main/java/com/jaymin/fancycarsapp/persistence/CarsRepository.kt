package com.jaymin.fancycarsapp.persistence

import android.content.Context
import androidx.room.Room

class CarsRepository(val context: Context) {

    private val databaseName = "cars"
    private val carsDatabase =
        Room.databaseBuilder(context, CarsDatabase::class.java, databaseName).build()


}