package com.jaymin.fancycarsapp.carscollection.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car(
    val id: Int? = null,
    val image: String? = null,
    val name: String? = null,
    val make: String? = null,
    val model: String? = null,
    val availability: Availability? = null
) : Parcelable {
    enum class Availability {
        IN_DEALERSHIP, OUT_OF_STOCK, UNAVAILABLE
    }
}

/*
@Parcelize
data class Car(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("img")
    val image: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("make")
    val make: String? = null,

    @field:SerializedName("model")
    val model: String? = null,

    @field:SerializedName("availability")
    val availability: String? = null
) : Parcelable
 */