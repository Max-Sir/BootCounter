package com.max.sir.bootcounter.domain

import com.max.sir.bootcounter.domain.repository.BootCounterRepository

class GetConfigInteractor(private val repository: BootCounterRepository) {
    suspend operator fun invoke() {

    }
}