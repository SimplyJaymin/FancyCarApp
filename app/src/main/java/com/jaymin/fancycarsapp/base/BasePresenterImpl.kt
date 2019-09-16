package com.jaymin.fancycarsapp.base

import java.lang.ref.WeakReference

abstract class BasePresenterImpl<V : BaseView> : BasePresenter<V> {

    // region properties
    private var weakReference: WeakReference<V>? = null

    val view: V?
        get() = weakReference?.get()

    private val isViewAttached: Boolean
        get() = weakReference != null && weakReference!!.get() != null
    // endregion

    // region BasePresenter methods
    override fun attachView(view: V) {
        if (!isViewAttached) {
            weakReference = WeakReference(view)
            view.setPresenter(this)
        }
    }

    override fun detachView() {
        weakReference?.clear()
        weakReference = null
    }
    // endregion
}