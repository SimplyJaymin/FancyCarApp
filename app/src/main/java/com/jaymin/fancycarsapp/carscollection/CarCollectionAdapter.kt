package com.jaymin.fancycarsapp.carscollection

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaymin.fancycarsapp.R
import com.jaymin.fancycarsapp.model.Car
import com.jaymin.fancycarsapp.model.Car.Availability.*
import kotlinx.android.synthetic.main.individual_car_item.view.*

class CarCollectionAdapter(
    private val cars: MutableList<Car>,
    private val context: Context
) :
    RecyclerView.Adapter<CarCollectionAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CarViewHolder =
        CarViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.individual_car_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(carViewHolder: CarViewHolder, position: Int) {
        val item = cars[position]
        with(item) {
            carViewHolder.carName.text = name
            carViewHolder.carMake.text = make
            carViewHolder.carModel.text = model
            carViewHolder.carAvailability.text = String.format(
                context.getString(R.string.carCollection_item_availabilityText),
                when (availability) {
                    IN_DEALERSHIP -> context.getString(R.string.carCollection_item_availabilityInDealershipText)
                    OUT_OF_STOCK -> context.getString(R.string.carCollection_item_availabilityOutOfStockText)
                    UNAVAILABLE -> context.getString(R.string.carCollection_item_availabilityUnavailableText)
                    else -> ""
                }
            )
            carViewHolder.carAddToCart.visibility = when (availability) {
                IN_DEALERSHIP -> View.VISIBLE
                OUT_OF_STOCK, UNAVAILABLE -> View.GONE
                else -> View.GONE
            }
        }
    }

    fun addCars(carsToAdd: List<Car>) {
        cars.addAll(carsToAdd)
        notifyDataSetChanged()
    }

    fun removeAllCars() {
        cars.clear()
        notifyDataSetChanged()
    }

    class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val carName = view.carItemName
        val carMake = view.carItemMake
        val carModel = view.carItemModel
        val carAvailability = view.carItemAvailability
        val carAddToCart = view.carItemAddToCart
    }
}