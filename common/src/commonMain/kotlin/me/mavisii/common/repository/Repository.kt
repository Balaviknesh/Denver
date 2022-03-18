package me.mavisii.common.repository

import kotlinx.coroutines.flow.Flow
import me.mavisii.common.model.ResponseObject
import me.mavisii.common.model.ServiceResult


interface Repository {
    suspend fun getWeather(): Flow<ServiceResult<ResponseObject>>
}