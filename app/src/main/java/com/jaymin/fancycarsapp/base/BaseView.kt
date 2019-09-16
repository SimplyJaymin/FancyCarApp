package com.jaymin.fancycarsapp.base

interface BaseView {
    fun setPresenter(presenterImpl: BasePresenterImpl<*>)
}