package com.example.order
import com.example.order.Data.GlobalConstAndVars
import com.example.order.Data.ItemOfList
import com.example.order.app.domain.CreateListOfAllItemsFrom1CDBImpl
import org.junit.Test

import org.junit.Assert.*

class Tests {
    @Test
    fun `проверка ключа для главного списка`() {
        assertNotNull(GlobalConstAndVars.KEY_FOR_INFLATE_MAIN_LIST)
    }

    @Test
    fun `проверить работу конвертера списков`() {
        val repo= CreateListOfAllItemsFrom1CDBImpl()
        assertEquals(ItemOfList("1", "1", "3", "0"), repo.changeValues("1", "2", "3", "4"))

    }

    @Test
    fun `проверить не равна ли нулу STEP_FOR_WORK_LIST`() {
        assertNotEquals(0, GlobalConstAndVars.STEP_FOR_WORK_LIST)
    }
}


