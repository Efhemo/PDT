package com.efhems.socialvue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model(
    val category: String,
    val icon: String,
    val id: Int,
    val professionals: List<Professional>
): Parcelable

@Parcelize
data class Professional(
        val address: String,
        val created_date: String,
        val full_time: Boolean,
        val image: String,
        val message: String,
        val name: String,
        val rating: String
):Parcelable