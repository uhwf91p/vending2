package com.example.order.Repository

import com.example.order.Data.MainList

interface MainRepisitoryFrom1C {
    fun getListForChoice():List<MainList>
    fun getLastSavedListForChoice():List<MainList>
    fun getListFrom1C()
}