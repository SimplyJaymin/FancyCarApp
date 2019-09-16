package com.jaymin.fancycarsapp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(

//    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "make")
    val make: String,

    @ColumnInfo(name = "model")
    val model: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "availability")
    val availability: String
)