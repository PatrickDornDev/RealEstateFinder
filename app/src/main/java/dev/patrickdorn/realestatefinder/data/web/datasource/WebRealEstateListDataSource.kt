package dev.patrickdorn.realestatefinder.data.web.datasource

import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import dev.patrickdorn.realestatefinder.data.web.service.RealEstateListService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WebRealEstateListDataSource(private val realEstateListService: RealEstateListService) :
    RealEstateListDataSource {

    private val _data: MutableStateFlow<RealEstateListData> =
        MutableStateFlow(RealEstateListData.EMPTY)
    override val data: StateFlow<RealEstateListData>
        get() = _data

    override suspend fun loadRealEstateList() {
        _data.value = realEstateListService.loadRealEstateList()
    }
}
