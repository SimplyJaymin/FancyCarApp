package com.jaymin.fancycarsapp.carscollection

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import com.jaymin.fancycarsapp.R
import com.jaymin.fancycarsapp.base.BaseActivity
import com.jaymin.fancycarsapp.base.CoRoutinesDispatcherImpl
import com.jaymin.fancycarsapp.carscollection.CarsCollectionPresenter.SortType.*
import com.jaymin.fancycarsapp.model.Car
import kotlinx.android.synthetic.main.activity_main.*

class CarsCollectionActivity : BaseActivity(), ICarsCollectionView {

    // region properties
    private var presenter: CarsCollectionPresenter? = null
    private var carCollectionAdapter: CarCollectionAdapter? = null
    private var interactor: ICarsCollectionInteractor? = null
    private var isCarsCollectionDataLoading = false
    private val visibleCarsThreshold = 7
    private var currentOffset = 0
    private val maxNumberOfCarsPerPage = 10
    private var noMoreCarDataToLoad = false
    private var sortType = NONE
    // endregion

    // region lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carsCollectionToolbarSortingOptions.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent?.getItemAtPosition(position)?.let {
                    val newSortType = when (it) {
                        getString(R.string.sortingOption_name) -> NAME
                        getString(R.string.sortingOption_availability) -> AVAILABILITY
                        else -> NONE
                    }
                    if (newSortType != sortType) {
                        sortType = newSortType
                        when (sortType) {
                            NAME -> presenter?.sortByNameSelected(maxNumberOfCars = maxNumberOfCarsPerPage)
                            AVAILABILITY -> presenter?.sortByAvailabilitySelected(maxNumberOfCars = maxNumberOfCarsPerPage)
                            NONE -> presenter?.sortByNoneSelected(maxNumberOfCars = maxNumberOfCarsPerPage)
                        }
                    }
                }
            }
        }

        carCollectionAdapter =
            CarCollectionAdapter(cars = mutableListOf(), context = this@CarsCollectionActivity)
        val linearLayoutManager = LinearLayoutManager(this)

        carsCollectionView.layoutManager = linearLayoutManager
        carsCollectionView.adapter = carCollectionAdapter
        carsCollectionView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = linearLayoutManager.itemCount
                val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                if (!noMoreCarDataToLoad && !isCarsCollectionDataLoading && totalItemCount <= lastVisibleItem + visibleCarsThreshold) {
                    currentOffset += totalItemCount
                    presenter?.loadCars(
                        offset = currentOffset,
                        maxNumberOfCars = maxNumberOfCarsPerPage,
                        sortType = sortType
                    )
                }
            }
        })

        presenter?.loadCars(
            offset = currentOffset,
            maxNumberOfCars = maxNumberOfCarsPerPage,
            sortType = sortType
        )
    }
    // endregion

    // region BaseActivity methods
    override fun onActivitySetup() {
        interactor = CarsCollectionInteractor(context = this@CarsCollectionActivity)
        interactor?.let {
            presenter = CarsCollectionPresenter(interactor = it, dispatcher = CoRoutinesDispatcherImpl()).apply {
                attachView(this@CarsCollectionActivity)
            }
        }
    }
    // endregion

    // region ICarsCollectionView methods
    override fun clearCarsCollection() {
        currentOffset = 0
        carCollectionAdapter?.removeAllCars()
    }

    override fun showLoading() {
        carsCollectionProgressBar.visibility = View.VISIBLE
        isCarsCollectionDataLoading = true
    }

    override fun hideLoading() {
        carsCollectionProgressBar.visibility = View.GONE
        isCarsCollectionDataLoading = false
    }

    override fun showCars(cars: List<Car>) {
        carCollectionAdapter?.addCars(carsToAdd = cars)
    }

    override fun noMoreCarsToLoad() {
        noMoreCarDataToLoad = true
    }
    // endregion
}
