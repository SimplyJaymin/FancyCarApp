package com.jaymin.fancycarsapp.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.jaymin.fancycarsapp.R
import com.jaymin.fancycarsapp.base.BaseActivity
import com.jaymin.fancycarsapp.base.CoRoutinesDispatcherImpl
import com.jaymin.fancycarsapp.carscollection.CarsCollectionActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity(), ISplashView {

    // region properties
    private var presenter: ISplashPresenter? = null
    private var interactor: ISplashInteractor? = null
    // endregion

    // region lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter?.onPageLoad()
    }
    // endregion

    // region ISplashView methods
    override fun showLoadingAnimation() {
        val rotateAnimation = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 1000
            repeatCount = Animation.INFINITE
        }

        splashLoadingImage.startAnimation(rotateAnimation)
    }

    override fun hideLoadingAnimation() = splashLoadingImage.clearAnimation()

    override fun showLoadingMessage() {
        splashLoadingMessage.text = getString(R.string.splash_message_loadingInProgress)
    }

    override fun showLoadingCompleteMessage() {
        splashLoadingMessage.text = getString(R.string.splash_message_loadingComplete)
    }

    override fun showDataPersistenceErrorMessage() {
        splashLoadingMessage.text = getString(R.string.splash_message_persistFailure)
    }

    override fun navigateToNextScreen() {
        val intent = Intent(this@SplashActivity, CarsCollectionActivity::class.java)
        startActivity(intent)
        finish()
    }
    // endregion

    // region BaseActivity methods
    override fun onActivitySetup() {
        interactor = SplashInteractor(context = this@SplashActivity)
        interactor?.let {
            presenter = SplashPresenter(interactor = it, dispatcher = CoRoutinesDispatcherImpl()).apply {
                attachView(this@SplashActivity)
            }
        }
    }
    // endregion
}
