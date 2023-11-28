package dev.patrickdorn.realestatefinder.data.web.repository

import dev.patrickdorn.realestatefinder.ui.data.RealEstateListItem
import kotlinx.coroutines.flow.Flow

interface RealEstateListRepository {

    val realEstateList: Flow<List<RealEstateListItem>>

    suspend fun updateRealEstateList()
}