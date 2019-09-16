package com.jaymin.fancycarsapp.base

interface BasePresenter<V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}