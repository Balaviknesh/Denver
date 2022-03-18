package me.mavisii.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.mavisii.common.model.ResponseObject
import me.mavisii.common.model.ServiceResult
import me.mavisii.common.repository.RepositoryImpl

class MainViewModel(private val repositoryImpl: RepositoryImpl) : ViewModel() {

    private var _liveValue = MutableLiveData<String>()
    var liveValue: LiveData<String> = _liveValue

    fun getWeather() {
        viewModelScope.launch {
            repositoryImpl.getWeather().collectLatest {
                if (it is ServiceResult.Error) {
                    _liveValue.value = "Error"
                } else {
                    _liveValue.value =
                        ((it as? ServiceResult.Success)?.data as? ResponseObject.WeatherResponse)?.weather?.location
                            ?.localtime

                }
            }


        }


    }

}