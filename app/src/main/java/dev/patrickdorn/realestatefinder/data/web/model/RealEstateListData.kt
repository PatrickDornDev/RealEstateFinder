package dev.patrickdorn.realestatefinder.data.web.model

import kotlinx.serialization.Serializable

@Serializable
data class RealEstateListData(
    val results: List<RealEstateData?> = emptyList()
) {
    companion object {
        val EMPTY = RealEstateListData(emptyList())
    }
}


@Serializable
data class RealEstateData(
    val id: String? = null,
    val listing: RealEstateListing? = null
)

@Serializable
data class RealEstateListing(
    val prices: RealEstatePrices? = null,
    val address: RealEstateAddress? = null,
    val localization: RealEstateLocalization? = null
)

@Serializable
data class RealEstatePrices(
    val currency: String? = null,
    val buy: RealEstateBuyPrice? = null
)

@Serializable
data class RealEstateAddress(
    val street: String? = null,
    val locality: String? = null
)

@Serializable
data class RealEstateLocalization(
    val primary: String? = null,
    val de: RealEstateLanguageLocalization? = null
)

@Serializable
data class RealEstateLanguageLocalization(
    val text: RealEstateText? = null,
    val attachments: List<RealEstateAttachments?>? = null
)

@Serializable
data class RealEstateText(
    val title: String? = null
)

@Serializable
data class RealEstateAttachments(
    val type: String? = null,
    val url: String? = null
)

@Serializable
data class RealEstateBuyPrice(
    val price: Long? = null
)
