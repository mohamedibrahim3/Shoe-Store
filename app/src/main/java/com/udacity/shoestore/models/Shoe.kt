package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoes(var name: String, var size: Int, var company: String, var description: String,
                val images: List<String> = mutableListOf()) : Parcelable