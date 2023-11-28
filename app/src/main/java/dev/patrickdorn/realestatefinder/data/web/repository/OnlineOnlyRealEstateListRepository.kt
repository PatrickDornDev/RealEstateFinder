package dev.patrickdorn.realestatefinder.data.web.repository

import dev.patrickdorn.realestatefinder.data.web.datasource.RealEstateListDataSource
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import dev.patrickdorn.realestatefinder.ui.data.RealEstateListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnlineOnlyRealEstateListRepository(
    private val realEstateListDataSource: RealEstateListDataSource,
    private val modelMapper: ModelMapper<RealEstateListData, List<RealEstateListItem>>
) : RealEstateListRepository {

    override val realEstateList: Flow<List<RealEstateListItem>> = realEstateListDataSource.data
        .map(modelMapper::map)

    override suspend fun updateRealEstateList() = realEstateListDataSource.loadRealEstateList()
}
