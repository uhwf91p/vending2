package com.example.order.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItem(var id1: String, var id2: String, var name:String, var value:String):Parcelable