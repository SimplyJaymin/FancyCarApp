package com.jaymin.fancycarsapp

import android.content.Context
import android.support.test.InstrumentationRegistry
import com.jaymin.fancycarsapp.carscollection.CarsCollectionPresenter
import com.jaymin.fancycarsapp.carscollection.ICarsCollectionInteractor
import com.jaymin.fancycarsapp.carscollection.ICarsCollectionView
import com.jaymin.fancycarsapp.persistence.Car
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CarsCollectionPresenterTest {

    //region properties
    private lateinit var context: Context
    private lateinit var presenter: CarsCollectionPresenter
    @Mock
    private lateinit var view: ICarsCollectionView
    @Mock
    private lateinit var interactor: ICarsCollectionInteractor
    // endregion

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        context = InstrumentationRegistry.getInstrumentation().targetContext
        presenter = CarsCollectionPresenter(
            interactor = interactor,
            dispatcher = MockedCoRoutinesDispatcher()
        )
        presenter.attachView(view = view)
    }

    @Test
    fun testConvertPersistedCarModelWithAvailabilityInDealership() {
        val dummyName = "dummy name"
        val dummyMake = "dummy make"
        val dummyModel = "dummy model"
        val dummyAvailability = "In Dealership"
        val dummyImage = "dummy image path"
        val dummyPersistedCarModel = Car(
            name = dummyName,
            make = dummyMake,
            model = dummyModel,
            availability = dummyAvailability,
            image = dummyImage
        )
        val convertedCarModel =
            presenter.convertPersistedCarModel(persistedCarModel = dummyPersistedCarModel)
        assertNotNull(convertedCarModel)
        assertEquals(convertedCarModel.name, dummyName)
        assertEquals(convertedCarModel.make, dummyMake)
        assertEquals(convertedCarModel.model, dummyModel)
        assertEquals(convertedCarModel.image, dummyImage)
        assertEquals(
            convertedCarModel.availability,
            com.jaymin.fancycarsapp.model.Car.Availability.IN_DEALERSHIP
        )
    }

    @Test
    fun testConvertPersistedCarModelWithAvailabilityOutOfStock() {
        val dummyName = "dummy name"
        val dummyMake = "dummy make"
        val dummyModel = "dummy model"
        val dummyAvailability = "Out of Stock"
        val dummyImage = "dummy image path"
        val dummyPersistedCarModel = Car(
            name = dummyName,
            make = dummyMake,
            model = dummyModel,
            availability = dummyAvailability,
            image = dummyImage
        )
        val convertedCarModel =
            presenter.convertPersistedCarModel(persistedCarModel = dummyPersistedCarModel)
        assertNotNull(convertedCarModel)
        assertEquals(convertedCarModel.name, dummyName)
        assertEquals(convertedCarModel.make, dummyMake)
        assertEquals(convertedCarModel.model, dummyModel)
        assertEquals(convertedCarModel.image, dummyImage)
        assertEquals(
            convertedCarModel.availability,
            com.jaymin.fancycarsapp.model.Car.Availability.OUT_OF_STOCK
        )
    }

    @Test
    fun testConvertPersistedCarModelWithAvailabilityUnavailable() {
        val dummyName = "dummy name"
        val dummyMake = "dummy make"
        val dummyModel = "dummy model"
        val dummyAvailability = "Unavailable"
        val dummyImage = "dummy image path"
        val dummyPersistedCarModel = Car(
            name = dummyName,
            make = dummyMake,
            model = dummyModel,
            availability = dummyAvailability,
            image = dummyImage
        )
        val convertedCarModel =
            presenter.convertPersistedCarModel(persistedCarModel = dummyPersistedCarModel)
        assertNotNull(convertedCarModel)
        assertEquals(convertedCarModel.name, dummyName)
        assertEquals(convertedCarModel.make, dummyMake)
        assertEquals(convertedCarModel.model, dummyModel)
        assertEquals(convertedCarModel.image, dummyImage)
        assertEquals(
            convertedCarModel.availability,
            com.jaymin.fancycarsapp.model.Car.Availability.UNAVAILABLE
        )
    }

    @Test
    fun testClearCollectionIsCalledForSortByAvailabilitySelected() {
        val dummyMaxNumberOfCars = 10
        presenter.sortByAvailabilitySelected(maxNumberOfCars = dummyMaxNumberOfCars)
        verify(view, times(1)).clearCarsCollection()
    }

    @Test
    fun testClearCollectionIsCalledForSortByNameSelected() {
        val dummyMaxNumberOfCars = 10
        presenter.sortByNameSelected(maxNumberOfCars = dummyMaxNumberOfCars)
        verify(view, times(1)).clearCarsCollection()
    }

    @Test
    fun testClearCollectionIsCalledForSortByNoneSelected() {
        val dummyMaxNumberOfCars = 10
        presenter.sortByNoneSelected(maxNumberOfCars = dummyMaxNumberOfCars)
        verify(view, times(1)).clearCarsCollection()
    }

    @Test
    fun testLoadCarsWithSortTypeNone() {
        val dummyMaxNumberOfCars = 10
        val dummyOffset = 1
        presenter.loadCars(
            offset = 1,
            maxNumberOfCars = dummyMaxNumberOfCars,
            sortType = CarsCollectionPresenter.SortType.NONE
        )
        verify(view, times(1)).showLoading()
        verify(interactor, times(1)).getListOfCars(dummyOffset, dummyMaxNumberOfCars)
    }

    @Test
    fun testLoadCarsWithSortTypeName() {
        val dummyMaxNumberOfCars = 10
        val dummyOffset = 1
        presenter.loadCars(
            offset = 1,
            maxNumberOfCars = dummyMaxNumberOfCars,
            sortType = CarsCollectionPresenter.SortType.NAME
        )
        verify(view, times(1)).showLoading()
        verify(interactor, times(1)).getListOfCarsSortedByName(dummyOffset, dummyMaxNumberOfCars)
    }

    @Test
    fun testLoadCarsWithSortTypeAvailability() {
        val dummyMaxNumberOfCars = 10
        val dummyOffset = 1
        presenter.loadCars(
            offset = 1,
            maxNumberOfCars = dummyMaxNumberOfCars,
            sortType = CarsCollectionPresenter.SortType.AVAILABILITY
        )
        verify(view, times(1)).showLoading()
        verify(interactor, times(1)).getListOfCarsSortedByAvailability(
            dummyOffset,
            dummyMaxNumberOfCars
        )
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }

}