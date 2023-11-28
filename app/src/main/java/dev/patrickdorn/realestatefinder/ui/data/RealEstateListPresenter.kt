package dev.patrickdorn.realestatefinder.ui.data

import dev.patrickdorn.realestatefinder.data.local.Favorite
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateData
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import dev.patrickdorn.realestatefinder.data.web.repository.RealEstateListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class RealEstateListPresenter(repository: RealEstateListRepository) {

    val realEstateList: Flow<List<RealEstateListItem>> = repository.realEstateList
        .combine(repository.realEstateFavorites) { dataSource, favorites ->
            map(
                dataSource,
                favorites
            )
        }

    private fun map(
        input: RealEstateListData,
        favorites: List<Favorite>
    ): List<RealEstateListItem> {
        return buildList {
            input.results.forEach { data: RealEstateData? ->
                add(
                    RealEstateListItem(
                        id = data?.id ?: "",
                        price = displayPrice(
                            data?.listing?.prices?.buy?.price,
                            data?.listing?.prices?.currency
                        ),
                        title = data?.listing?.localization?.de?.text?.title ?: "",
                        address = displayAddress(
                            data?.listing?.address?.street,
                            data?.listing?.address?.locality
                        ),
                        url = data?.listing?.localization?.de?.attachments?.firstOrNull()?.url
                            ?: "",
                        isFavorite = favorites.any { it.listingId == data?.id }
                    )
                )
            }
        }
    }

    private fun displayPrice(price: Long?, currency: String?): String {
        if (price == null || currency == null) {
            return ""
        }
        return "$price $currency"
    }

    private fun displayAddress(street: String?, locality: String?): String {
        return listOfNotNull(street, locality).joinToString(separator = ", ")
    }
}