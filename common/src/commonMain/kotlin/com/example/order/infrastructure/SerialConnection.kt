package com.example.order.infrastructure

expect class SerialConnection {
    /**
     * Инициализирует и открывает Serial Port
     * @return true - если успешно открыто. false - если неуспешно
     */
    suspend fun connect(): Boolean

    /**
     * Закрывает Serial Port
     * @return true - если успешно закрыто. false - если неуспешно или порт не был ранее инициализирован
     */
    suspend fun disconnect(): Boolean

    /**
     * Записывает массив байт в открытый Serial Port
     * @return Число успешно записанных байт или -1, если была ошибка записи или порт не был ранее инициализирован
     */
    suspend fun write(data: ByteArray): Int
}

