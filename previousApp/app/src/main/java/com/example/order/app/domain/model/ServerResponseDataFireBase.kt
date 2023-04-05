package com.example.order.app.domain.model

import com.google.gson.annotations.SerializedName

data class ServerResponseDataFireBase(
   var collection:String="",
   var documentFB: String="",
   var field: String="",
   var value: String="",
   var theme:String="",
   var typeOfTests:String=""
)
