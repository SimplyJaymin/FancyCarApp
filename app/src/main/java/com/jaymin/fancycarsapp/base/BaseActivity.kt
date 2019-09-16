package com.jaymin.fancycarsapp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), BaseView {

    // region properties
    private var presenter: BasePresenter<*>? = null
    // endregion

    abstract fun onActivitySetup()

    // region lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivitySetup()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null
    }
    // endregion

    // region BaseView methods
    override fun setPresenter(presenterImpl: BasePresenterImpl<*>) {
        this.presenter = presenterImpl
    }
    // endregion
}