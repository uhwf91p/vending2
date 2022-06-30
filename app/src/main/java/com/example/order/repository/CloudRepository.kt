package com.example.order.repository

interface CloudRepository {
   fun getDataFromCloud()
   fun loadDatatoTheCloud()
}