package com.example.InfoTrade.data.mappers

import com.example.InfoTrade.data.local.CompanyListingEntity
import com.example.InfoTrade.data.remote.dto.CompanyInfoDto
import com.example.InfoTrade.domain.model.CompanyInfo
import com.example.InfoTrade.domain.model.CompanyListing

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