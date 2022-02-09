package com.example.order
import com.example.order.Data.Keys
import com.example.order.Data.MainList
import com.example.order.Repository.MainRepositoryFrom1CImpl
import org.junit.Test

import org.junit.Assert.*

class Tests {
    @Test
    fun `проверка ключа для главного списка`() {
        assertNotNull(Keys.KEY_FOR_INFLATE_MAIN_LIST)
    }

    @Test
    fun `проверить работу конвертера списков`() {
        val repo=MainRepositoryFrom1CImpl()
        assertEquals(MainList("1", "1", "3", "0"), repo.changeValues("1", "2", "3", "4"))

    }

    @Test
    fun `проверить не равна ли нулу STEP_FOR_WORK_LIST`() {
        assertNotEquals(0, Keys.STEP_FOR_WORK_LIST)
    }
}


