package com.jaymin.fancycarsapp.splash

import android.content.Context
import com.jaymin.fancycarsapp.persistence.Car
import com.jaymin.fancycarsapp.persistence.CarsDatabase

class SplashInteractor(private val context: Context) : ISplashInteractor {

    // region ISplashInteractor methods
    override fun loadDataIntoDatabase(cars: List<Car>): Boolean {
        Thread.sleep(2000)
        return try {
            val carDao = CarsDatabase.getInstance(context = context).carDao()
            carDao.deleteAllCars()
            carDao.insertAll(cars)
            true
        } catch (exception: Exception) {
            false
        }
    }
    // endregion
}