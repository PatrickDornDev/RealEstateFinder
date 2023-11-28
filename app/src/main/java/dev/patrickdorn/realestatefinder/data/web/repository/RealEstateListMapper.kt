package dev.patrickdorn.realestatefinder.data.web.repository

import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import dev.patrickdorn.realestatefinder.ui.data.RealEstateListItem

class RealEstateListMapper : ModelMapper<RealEstateListData, List<RealEstateListItem>> {
    override fun map(input: RealEstateListData): List<RealEstateListItem> {
        return buildList {
            input.results.forEach {
                add(
                    RealEstateListItem(
                        id = it?.id ?: "",
                        price = displayPrice(
                            it?.listing?.prices?.buy?.price,
                            it?.listing?.prices?.currency
                        ),
                        title = it?.listing?.localization?.de?.text?.title ?: "",
                        address = displayAddress(
                            it?.listing?.address?.street,
                            it?.listing?.address?.locality
                        ),
                        url = it?.listing?.localization?.de?.attachments?.firstOrNull()?.url ?: "",
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
