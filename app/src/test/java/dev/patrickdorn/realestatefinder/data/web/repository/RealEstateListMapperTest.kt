package dev.patrickdorn.realestatefinder.data.web.repository

import dev.patrickdorn.realestatefinder.buildRealEstateData
import dev.patrickdorn.realestatefinder.buildRealEstatePropertyList
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateAttachments
import org.junit.Test
import kotlin.test.assertEquals

class RealEstateListMapperTest {

    private val mapper = RealEstateListMapper()

    @Test
    fun `WHEN everything is null THEN return data with empty strings`() {
        val mockDataListWithNullValues = buildRealEstatePropertyList(
            buildRealEstateData(null, null, null, null, null, null, null)
        )

        val mapped = mapper.map(mockDataListWithNullValues)

        assertEquals("", mapped[0].id)
        assertEquals("", mapped[0].price)
        assertEquals("", mapped[0].title)
        assertEquals("", mapped[0].address)
        assertEquals("", mapped[0].url)
    }

    @Test
    fun `WHEN all properties are set, THEN return data with formatted fields`() {
        val mockDataListWithAllValuesSet = buildRealEstatePropertyList(
            buildRealEstateData(
                "1",
                "USD",
                999999,
                "Grove Street",
                "San Andreas",
                "House",
                RealEstateAttachments(
                    type = "IMAGE",
                    url = "https://example.com/"
                )
            )
        )

        val mapped = mapper.map(mockDataListWithAllValuesSet)

        assertEquals("1", mapped[0].id)
        assertEquals("999999 USD", mapped[0].price)
        assertEquals("House", mapped[0].title)
        assertEquals("Grove Street, San Andreas", mapped[0].address)
        assertEquals("https://example.com/", mapped[0].url)
    }

    @Test
    fun `WHEN price or currency null, THEN return data with empty string`() {
        val mockDataListWithCurrencyAndPriceSet = buildRealEstatePropertyList(
            buildRealEstateData(
                currency = "USD",
                price = 999999,
            )
        )
        val mockDataListWithCurrencyNullAndPriceSet = buildRealEstatePropertyList(
            buildRealEstateData(
                currency = null,
                price = 999999,
            )
        )
        val mockDataListWithCurrencySetAndPriceNull = buildRealEstatePropertyList(
            buildRealEstateData(
                currency = "USD",
                price = null,
            )
        )

        val mappedPriceAndCurrency = mapper.map(mockDataListWithCurrencyAndPriceSet)
        val mappedWithCurrencyNullAndPriceSet = mapper.map(mockDataListWithCurrencyNullAndPriceSet)
        val mappedWithCurrencySetAndPriceNull = mapper.map(mockDataListWithCurrencySetAndPriceNull)

        assertEquals("999999 USD", mappedPriceAndCurrency[0].price)
        assertEquals("", mappedWithCurrencyNullAndPriceSet[0].price)
        assertEquals("", mappedWithCurrencySetAndPriceNull[0].price)
    }

    @Test
    fun `WHEN street or locality not set, THEN omit , as separator`() {
        val streetAndLocalitySet = buildRealEstatePropertyList(
            buildRealEstateData(
                street = "Grove Street",
                locality = "San Andreas",
            )
        )
        val streetNullAndLocalitySet = buildRealEstatePropertyList(
            buildRealEstateData(
                street = null,
                locality = "San Andreas",
            )
        )
        val streetSetAndLocalityNull = buildRealEstatePropertyList(
            buildRealEstateData(
                street = "Grove Street",
                locality = null
            )
        )

        val mappedWithStreetAndLocalitySet = mapper.map(streetAndLocalitySet)
        val mappedWithStreetNullAndLocalitySet = mapper.map(streetNullAndLocalitySet)
        val mappedWithStreetSetAndLocalityNull = mapper.map(streetSetAndLocalityNull)

        assertEquals("Grove Street, San Andreas", mappedWithStreetAndLocalitySet[0].address)
        assertEquals("San Andreas", mappedWithStreetNullAndLocalitySet[0].address)
        assertEquals("Grove Street", mappedWithStreetSetAndLocalityNull[0].address)
    }
}
