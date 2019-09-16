package com.jaymin.fancycarsapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun getAllCars(): List<Car>

    @Query("SELECT * FROM cars LIMIT :maxNumberOfCars OFFSET :offsetNumberOfCars")
    fun getCars(offsetNumberOfCars: Int, maxNumberOfCars: Int): List<Car>

    @Query("SELECT * FROM cars ORDER BY name LIMIT :maxNumberOfCars OFFSET :offsetNumberOfCars")
    fun getCarsSortedByName(offsetNumberOfCars: Int, maxNumberOfCars: Int): List<Car>

    @Query("SELECT * FROM cars ORDER BY availability LIMIT :maxNumberOfCars OFFSET :offsetNumberOfCars")
    fun getCarsSortedByAvailability(offsetNumberOfCars: Int, maxNumberOfCars: Int): List<Car>

    @Query("SELECT * FROM cars WHERE id IN (:carIds)")
    fun loadAllCarsByIds(carIds: IntArray): List<Car>

    @Query("SELECT * FROM cars WHERE id =:id")
    fun findCarById(id: Int): Car

    @Insert
    fun insertAll(cars: List<Car>)

    @Delete
    fun delete(car: Car)

    @Query("DELETE FROM cars")
    fun deleteAllCars()
}