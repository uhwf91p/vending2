package com.example.order.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItem(var id1: String, var dataType: String, var ticketNumber:String, var value:String):Parcelable