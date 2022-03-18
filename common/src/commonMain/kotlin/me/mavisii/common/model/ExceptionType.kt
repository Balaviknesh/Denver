package me.mavisii.common.model

enum class ExceptionType (val serviceException: ServiceException){
    NETWORK_ERROR(ServiceException("", "")),
    GENERIC_ERROR(ServiceException("", ""))
}