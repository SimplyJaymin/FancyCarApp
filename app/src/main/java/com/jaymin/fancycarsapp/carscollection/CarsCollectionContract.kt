package com.jaymin.fancycarsapp.carscollection

import com.jaymin.fancycarsapp.base.BaseView
import com.jaymin.fancycarsapp.model.Car

interface ICarsCollectionView : BaseView {
    fun showLoading()
    fun hideLoading()
    fun showCars(cars: List<Car>)
    fun noMoreCarsToLoad()
    fun clearCarsCollection()
}

interface ICarsCollectionPresenter {
    fun loadCars(offset: Int, maxNumberOfCars: Int, sortType: CarsCollectionPresenter.SortType)
    fun convertPersistedCarModel(persistedCarModel: com.jaymin.fancycarsapp.persistence.Car) : Car
    fun sortByNameSelected(maxNumberOfCars: Int)
    fun sortByAvailabilitySelected(maxNumberOfCars: Int)
    fun sortByNoneSelected(maxNumberOfCars: Int)
}

interface ICarsCollectionInteractor {
    fun getListOfCars(offset: Int, maxNumberOfCars: Int): List<com.jaymin.fancycarsapp.persistence.Car>
    fun getListOfCarsSortedByName(offset: Int, maxNumberOfCars: Int): List<com.jaymin.fancycarsapp.persistence.Car>
    fun getListOfCarsSortedByAvailability(offset: Int, maxNumberOfCars: Int): List<com.jaymin.fancycarsapp.persistence.Car>
}