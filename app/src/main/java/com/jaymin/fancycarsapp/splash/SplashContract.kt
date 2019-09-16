package com.jaymin.fancycarsapp.splash

import com.jaymin.fancycarsapp.base.BaseView
import com.jaymin.fancycarsapp.persistence.Car

interface ISplashView : BaseView {
    fun showLoadingAnimation()
    fun hideLoadingAnimation()
    fun navigateToNextScreen()
    fun showLoadingMessage()
    fun showLoadingCompleteMessage()
    fun showDataPersistenceErrorMessage()
}

interface ISplashPresenter {
    fun onPageLoad()
    fun onDataPersistSuccess()
    fun onDataPersistFailure()
}

interface ISplashInteractor {
    fun loadDataIntoDatabase(cars: List<Car>): Boolean
}