package com.example.infotrade.data.mappers

import com.example.infotrade.data.local.CompanyListingEntity
import com.example.infotrade.data.remote.dto.CompanyInfoDto
import com.example.infotrade.domain.model.CompanyInfo
import com.example.infotrade.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol?: "",
        description = description?: "",
        name = name?: "",
        country = country?: "",
        industry = industry?: ""
    )
}