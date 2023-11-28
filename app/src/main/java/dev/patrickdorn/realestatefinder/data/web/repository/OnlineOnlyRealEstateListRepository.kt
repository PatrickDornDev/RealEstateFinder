package dev.patrickdorn.realestatefinder.data.web.repository

import dev.patrickdorn.realestatefinder.data.local.Favorite
import dev.patrickdorn.realestatefinder.data.local.RealEstateFinderDatabase
import dev.patrickdorn.realestatefinder.data.web.datasource.RealEstateListDataSource
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import kotlinx.coroutines.flow.Flow

class OnlineOnlyRealEstateListRepository(
    private val realEstateListDataSource: RealEstateListDataSource,
    private val database: RealEstateFinderDatabase
) : RealEstateListRepository {

    override val realEstateList: Flow<RealEstateListData> = realEstateListDataSource.data
    override val realEstateFavorites: Flow<List<Favorite>> = database.favoriteDao().getAll()

    override suspend fun updateRealEstateList() {
        realEstateListDataSource.loadRealEstateList()
    }

    override suspend fun toggleFavorite(id: String) {
        val favorite = database.favoriteDao().getFavoriteById(id)
        if (favorite == null) {
            database.favoriteDao().insert(Favorite(listingId = id))
        } else {
            database.favoriteDao().delete(favorite)
        }
    }
}
