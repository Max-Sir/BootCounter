package com.max.sir.bootcounter.domain.entity

data class AppConfigDomainEntity(
    val id: Int = 1,
    val dismissalsAllowed: Int = 5,
    val dismissalInterval: Int = 20
)

