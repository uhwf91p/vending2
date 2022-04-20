package com.example.order.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemOfList(var id1: String, var id2: String, var name:String, var value:String):Parcelable