package com.jaymin.fancycarsapp.carscollection

import com.jaymin.fancycarsapp.base.BasePresenterImpl
import com.jaymin.fancycarsapp.base.ICoRoutinesDispatcher
import com.jaymin.fancycarsapp.persistence.Car
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.jaymin.fancycarsapp.carscollection.model.Car as CarModel

class CarsCollectionPresenter(private val interactor: ICarsCollectionInteractor,
                              private val dispatcher: ICoRoutinesDispatcher) :
    BasePresenterImpl<ICarsCollectionView>(), ICarsCollectionPresenter {

    enum class SortType {
        NONE, NAME, AVAILABILITY
    }

    // region ICarsCollectionPresenter methods
    override fun loadCars(offset: Int, maxNumberOfCars: Int, sortType: SortType) {
        view?.showLoading()
        GlobalScope.launch(context = dispatcher.ui()) {
            val persistedCars = withContext(dispatcher.io()) {
                when (sortType) {
                    SortType.NONE -> interactor.getListOfCars(
                        offset = offset,
                        maxNumberOfCars = maxNumberOfCars
                    )
                    SortType.NAME -> interactor.getListOfCarsSortedByName(
                        offset = offset, maxNumberOfCars = maxNumberOfCars
                    )
                    SortType.AVAILABILITY -> interactor.getListOfCarsSortedByAvailability(
                        offset = offset,
                        maxNumberOfCars = maxNumberOfCars
                    )
                }
            }

            val result = persistedCars.map { convertPersistedCarModel(persistedCarModel = it) }
            view?.hideLoading()
            if (result.isEmpty()) {
                view?.noMoreCarsToLoad()
            } else {
                view?.showCars(cars = result)
            }
        }
    }

    override fun convertPersistedCarModel(persistedCarModel: Car): CarModel =
        with(persistedCarModel) {
            CarModel(
                id = id,
                image = image,
                model = model,
                make = make,
                name = name,
                availability = when (availability) {
                    "In Dealership" -> CarModel.Availability.IN_DEALERSHIP
                    "Out of Stock" -> CarModel.Availability.OUT_OF_STOCK
                    "Unavailable" -> CarModel.Availability.UNAVAILABLE
                    else -> null
                }
            )
        }

    override fun sortByNameSelected(maxNumberOfCars: Int) {
        view?.clearCarsCollection()
        loadCars(offset = 0, maxNumberOfCars = maxNumberOfCars, sortType = SortType.NAME)
    }

    override fun sortByAvailabilitySelected(maxNumberOfCars: Int) {
        view?.clearCarsCollection()
        loadCars(offset = 0, maxNumberOfCars = maxNumberOfCars, sortType = SortType.AVAILABILITY)
    }

    override fun sortByNoneSelected(maxNumberOfCars: Int) {
        view?.clearCarsCollection()
        loadCars(offset = 0, maxNumberOfCars = maxNumberOfCars, sortType = SortType.NONE)
    }
    // endregion
}