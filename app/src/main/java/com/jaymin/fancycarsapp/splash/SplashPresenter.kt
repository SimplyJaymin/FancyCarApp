package com.jaymin.fancycarsapp.splash

import com.jaymin.fancycarsapp.base.BasePresenterImpl
import com.jaymin.fancycarsapp.base.ICoRoutinesDispatcher
import com.jaymin.fancycarsapp.persistence.Car
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class SplashPresenter(private val interactor: ISplashInteractor,
                      private val dispatcher: ICoRoutinesDispatcher
) : BasePresenterImpl<ISplashView>(),
    ISplashPresenter {

    private val availabilityValues = listOf("In Dealership", "Out of Stock", "Unavailable")
    private val carMakeValues =
        listOf("Make A", "Make B", "Make C", "Make D", "Make E", "Make F", "Make G")
    private val carModelValues =
        listOf("Model A", "Model B", "Model C", "Model D", "Model E", "Model F", "Model G")
    private val carNameValues =
        listOf("Name A", "Name B", "Name C", "Name D", "Name E", "Name F", "Name G")
    private val numberOfRandomCarsToGenerate = 2000

    // region ISplashPresenter methods
    override fun onPageLoad() {
        view?.apply {
            showLoadingAnimation()
            showLoadingMessage()
        }
        val randomCars = mutableListOf<Car>()
        for (count in 0 until numberOfRandomCarsToGenerate) {
            val randomAvailabilityValue =
                availabilityValues[Random.nextInt(availabilityValues.size)]
            val randomMakeValue = carMakeValues[Random.nextInt(carMakeValues.size)]
            val randomModelValue = carModelValues[Random.nextInt(carModelValues.size)]
            val randomNameValue = carNameValues[Random.nextInt(carNameValues.size)]
            val car = Car(
                name = randomNameValue,
                make = randomMakeValue,
                model = randomModelValue,
                availability = randomAvailabilityValue,
                image = ""
            )
            randomCars.add(car)
        }

        GlobalScope.launch(context = dispatcher.ui()) {
            val result = withContext(dispatcher.io()) {
                interactor.loadDataIntoDatabase(cars = randomCars)
            }

            if (result) {
                onDataPersistSuccess()
            } else {
                onDataPersistFailure()
            }
        }
    }

    override fun onDataPersistSuccess() {
        view?.apply {
            hideLoadingAnimation()
            showLoadingCompleteMessage()
            navigateToNextScreen()
        }
    }

    override fun onDataPersistFailure() {
        view?.showDataPersistenceErrorMessage()
    }
    // endregion
}