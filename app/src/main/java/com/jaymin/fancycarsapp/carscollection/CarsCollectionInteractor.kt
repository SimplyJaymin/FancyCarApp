package com.jaymin.fancycarsapp.carscollection

import android.content.Context
import com.jaymin.fancycarsapp.persistence.Car
import com.jaymin.fancycarsapp.persistence.CarsDatabase

class CarsCollectionInteractor(private val context: Context) : ICarsCollectionInteractor {

    // region ICarsCollectionInteractor methods
    override fun getListOfCars(offset: Int, maxNumberOfCars: Int): List<Car> {
        Thread.sleep(2000)
        return CarsDatabase.getInstance(context).carDao()
            .getCars(offsetNumberOfCars = offset, maxNumberOfCars = maxNumberOfCars)
    }

    override fun getListOfCarsSortedByName(offset: Int, maxNumberOfCars: Int): List<Car> {
        Thread.sleep(2000)
        return CarsDatabase.getInstance(context).carDao()
            .getCarsSortedByName(offsetNumberOfCars = offset, maxNumberOfCars = maxNumberOfCars)
    }

    override fun getListOfCarsSortedByAvailability(offset: Int, maxNumberOfCars: Int): List<Car> {
        Thread.sleep(2000)
        return CarsDatabase.getInstance(context).carDao()
            .getCarsSortedByAvailability(offsetNumberOfCars = offset, maxNumberOfCars = maxNumberOfCars)
    }
    // endregion

}