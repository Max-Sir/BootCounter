package com.max.sir.bootcounter.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.max.sir.bootcounter.data.AppConfigRepository
import com.max.sir.bootcounter.data.BootCounterDatabase
import com.max.sir.bootcounter.data.BootEventRepository
import com.max.sir.bootcounter.data.entity.AppConfig
import com.max.sir.bootcounter.data.entity.BootEvent
import kotlinx.coroutines.launch

class BootCounterViewModel(application: Application) : AndroidViewModel(application) {

    private val bootEventRepository: BootEventRepository
    private val appConfigRepository: AppConfigRepository

    private val _bootEvents = MutableLiveData<List<BootEvent>>()
    val bootEvents: LiveData<List<BootEvent>> = _bootEvents

    private val _config =
        MutableLiveData<AppConfig>(AppConfig(id = 1, dismissalInterval = 20, dismissalsAllowed = 5))
    val config: LiveData<AppConfig> = _config

    init {

        val bootEventDao = BootCounterDatabase.getDatabase(application).bootEventDao()
        bootEventRepository = BootEventRepository(bootEventDao)

        val appConfigDao = BootCounterDatabase.getDatabase(application).appConfigDao()
        appConfigRepository = AppConfigRepository(appConfigDao)

        viewModelScope.launch {
            appConfigRepository.updateConfig(
                AppConfig(
                    id = 1,
                    dismissalInterval = 20,
                    dismissalsAllowed = 5
                )
            )
        }

        loadBootEvents()
        loadConfig()
    }

    private fun loadBootEvents() {
        viewModelScope.launch {
            _bootEvents.postValue(bootEventRepository.getAllBootEvents())
        }
    }

    private fun loadConfig() {
        viewModelScope.launch {
            _config.postValue(appConfigRepository.getConfig())
        }
    }

    fun updateConfig(newConfig: AppConfig) {
        viewModelScope.launch {
            appConfigRepository.updateConfig(newConfig)
        }
        _config.postValue(newConfig)
    }
}
