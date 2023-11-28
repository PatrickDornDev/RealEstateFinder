package dev.patrickdorn.realestatefinder

import dev.patrickdorn.realestatefinder.data.web.model.RealEstateAddress
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateAttachments
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateBuyPrice
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateData
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateLanguageLocalization
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListing
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateLocalization
import dev.patrickdorn.realestatefinder.data.web.model.RealEstatePrices
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateText

fun buildRealEstatePropertyList(vararg realEstateData: RealEstateData?): RealEstateListData {
    return RealEstateListData(results = realEstateData.asList())
}

fun buildRealEstateData(
    id: String? = "0",
    currency: String? = "USD",
    price: Long? = 999999,
    street: String? = "Grove Street",
    locality: String? = "San Andreas",
    title: String? = "House at the end of the street",
    vararg attachments: RealEstateAttachments? =
        listOf(
            RealEstateAttachments(
                type = "IMAGE",
                url = "https://example.com/"
            )
        ).toTypedArray()
) = RealEstateData(
    id = id,
    listing = RealEstateListing(
        prices = RealEstatePrices(
            currency = currency,
            buy = RealEstateBuyPrice(price)
        ),
        address = RealEstateAddress(
            street = street,
            locality = locality
        ),
        localization = RealEstateLocalization(
            de = RealEstateLanguageLocalization(
                text = RealEstateText(
                    title = title
                ),
                attachments = attachments.asList()
            )
        )
    )
)
