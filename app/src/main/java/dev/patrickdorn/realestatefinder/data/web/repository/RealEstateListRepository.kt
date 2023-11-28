package dev.patrickdorn.realestatefinder.data.web.repository

import dev.patrickdorn.realestatefinder.data.local.Favorite
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import kotlinx.coroutines.flow.Flow

interface RealEstateListRepository {

    val realEstateList: Flow<RealEstateListData>
    val realEstateFavorites: Flow<List<Favorite>>

    suspend fun updateRealEstateList()
    suspend fun toggleFavorite(id: String)
}