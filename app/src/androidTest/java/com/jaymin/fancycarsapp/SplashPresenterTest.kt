package com.jaymin.fancycarsapp

import android.content.Context
import android.support.test.InstrumentationRegistry
import com.jaymin.fancycarsapp.splash.ISplashInteractor
import com.jaymin.fancycarsapp.splash.ISplashView
import com.jaymin.fancycarsapp.splash.SplashPresenter
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SplashPresenterTest {

    //region properties
    private lateinit var context: Context
    private lateinit var presenter: SplashPresenter
    @Mock
    private lateinit var view: ISplashView
    @Mock
    private lateinit var interactor: ISplashInteractor
    // endregion

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        context = InstrumentationRegistry.getInstrumentation().targetContext
        interactor = Mockito.mock(ISplashInteractor::class.java)
        presenter =
            SplashPresenter(interactor = interactor, dispatcher = MockedCoRoutinesDispatcher())
        view = Mockito.mock(ISplashView::class.java)
        presenter.attachView(view = view)
    }

    @Test
    fun testLoadingAnimationStartsOnPageLoad() {
        presenter.onPageLoad()
        verify(view, times(1)).showLoadingAnimation()
    }

    @Test
    fun testLoadingMessageIsShownOnPageLoad() {
        presenter.onPageLoad()
        verify(view, times(1)).showLoadingMessage()
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }

}