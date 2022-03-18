package me.mavisii.common.repository

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import me.mavisii.common.model.ResponseObject.*
import me.mavisii.common.model.ServiceException
import me.mavisii.common.model.ServiceResult
import me.mavisii.common.service.ApiService
import kotlinx.serialization.json.Json
import me.mavisii.common.model.ResponseObject
import me.mavisii.common.service.KtorService


class RepositoryImpl: Repository {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val apiClient: HttpClient by lazy { KtorService.getClient() }
    private val apiService = ApiService(apiClient, "http://192.168.1.92:8081/")

    private fun transformMetaDataResponse(response: MetaDataResponse): ServiceResult<MetaDataResponse> {
        response.apply {
            return if(metadata.statusCode != 200)
                ServiceResult.Error(ServiceException(metadata.tags?.errorCode.toString(), metadata.tags?.errorMessages.toString()))
            else
                ServiceResult.Success(response)
        }

    }

    private suspend inline fun <reified T> processCall(call: () -> HttpResponse): ServiceResult<T> {
        return try {
            val serviceCallBack = call()
            if(serviceCallBack.status.isSuccess()){
                ServiceResult.Success(Json.decodeFromString(serviceCallBack.receive()))
            }
            else{
                ServiceResult.Error(processErrorBody(serviceCallBack))
            }
        }catch (exception: Exception){
            ServiceResult.Error(ServiceException("E500", exception.message ?: "No Error Message"))
        }

    }

    private suspend fun processErrorBody(response: HttpResponse): ServiceException{
        val errorResponse: MetaDataResponse = Json.decodeFromString(response.receive())
        val status = errorResponse.metadata.tags?.errorCode ?: errorResponse.metadata.statusCode
        val message = errorResponse.metadata.tags?.errorMessages ?: errorResponse.metadata.statusMessage

        return ServiceException(status.toString(), message)

    }

    override suspend fun getWeather(): Flow<ServiceResult<ResponseObject>> {

        return flow {
                val result: ServiceResult<WeatherResponse> = processCall { apiService.getCurrentWeather("Naperville") }
                emit(result)
        }.map{ result ->
            val transformedResult: ServiceResult<MetaDataResponse> = when(result){
                is ServiceResult.Success -> transformMetaDataResponse(result.data)
                is ServiceResult.Error -> result
            }
            transformedResult
        }.catch { exception ->
            emit(ServiceResult.Error(ServiceException("E501", exception.message.toString())))
        }.flowOn(ioDispatcher)
    }

}
