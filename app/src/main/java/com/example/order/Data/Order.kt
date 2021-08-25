package com.example.order.Data

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order (var mainList: MainList):Parcelable