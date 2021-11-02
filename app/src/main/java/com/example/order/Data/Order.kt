package com.example.order.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order (var mainList: MutableList<MainList>):Parcelable