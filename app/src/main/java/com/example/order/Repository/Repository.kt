package com.example.order.Repository

import com.example.order.Data.MainList

interface Repository {
   fun  getMainList():List<MainList>
}