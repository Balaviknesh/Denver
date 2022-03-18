package me.mavisii.common.model

import kotlinx.serialization.Serializable

@Serializable
data class ServiceException(val errorCode: String, val errorMessage: String)
