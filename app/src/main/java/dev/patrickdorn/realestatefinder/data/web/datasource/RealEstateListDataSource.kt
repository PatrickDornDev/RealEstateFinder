package dev.patrickdorn.realestatefinder.data.web.datasource

import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import kotlinx.coroutines.flow.StateFlow

interface RealEstateListDataSource {

    val data: StateFlow<RealEstateListData>

    suspend fun loadRealEstateList()
}
