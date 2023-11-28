package dev.patrickdorn.realestatefinder.data.web.repository

import app.cash.turbine.test
import dev.patrickdorn.realestatefinder.data.web.datasource.RealEstateListDataSource
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import dev.patrickdorn.realestatefinder.ui.data.RealEstateListItem
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertNotNull

class OnlineOnlyRealEstateListRepositoryTest {

    private val dataSource = mockk<RealEstateListDataSource>(relaxed = true).apply {
        every { data } returns MutableStateFlow(RealEstateListData.EMPTY)
    }

    private val mapper: ModelMapper<RealEstateListData, List<RealEstateListItem>> = object :
        ModelMapper<RealEstateListData, List<RealEstateListItem>> {
        override fun map(input: RealEstateListData): List<RealEstateListItem> {
            return listOf(RealEstateListItem("", "0", "", "", ""))
        }
    }

    private val repository = OnlineOnlyRealEstateListRepository(dataSource, mapper)

    @Test
    fun `WHEN DataSource emits an item, THEN Repository gets notified`() = runTest {
        repository.realEstateList.test {
            assertNotNull(awaitItem())
        }
    }

    @Test
    fun `WHEN updateRealEstateList is called dataSource is called`() = runTest {
        repository.updateRealEstateList()
        coVerify(atLeast = 1, atMost = 1) { dataSource.loadRealEstateList() }
    }
}