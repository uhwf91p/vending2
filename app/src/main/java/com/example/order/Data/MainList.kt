package com.example.order.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainList (val id1:Int, val id2:Int, /*var id3:Int,*/var name:String, var value:String):Parcelable