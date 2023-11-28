package dev.patrickdorn.realestatefinder.data.web.datasource

import dev.patrickdorn.realestatefinder.buildRealEstateData
import dev.patrickdorn.realestatefinder.buildRealEstatePropertyList
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import dev.patrickdorn.realestatefinder.data.web.service.RealEstateListService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class WebRealEstateListDataSourceTest {

    private val mockDataList = buildRealEstatePropertyList(
        buildRealEstateData("1"),
        buildRealEstateData("2"),
        buildRealEstateData("3"),
    )

    private val mockDataService = mockk<RealEstateListService>().apply {
        coEvery { loadRealEstateList() } returns mockDataList
    }

    private val dataSourceUnderTest = WebRealEstateListDataSource(mockDataService)

    @Test
    fun `WHEN no data is loaded yet THEN emitted value of data source is EMPTY`() = runTest {
        assertEquals(RealEstateListData.EMPTY, dataSourceUnderTest.data.value)
    }

    @Test
    fun `WHEN refresh is triggered THEN the new data is emitted`() = runTest {
        assertEquals(RealEstateListData.EMPTY, dataSourceUnderTest.data.value)
        dataSourceUnderTest.loadRealEstateList()
        assertEquals(expected = mockDataList, actual = dataSourceUnderTest.data.value)
    }
}
