package dev.patrickdorn.realestatefinder.data.web.repository

import app.cash.turbine.test
import dev.patrickdorn.realestatefinder.data.local.Favorite
import dev.patrickdorn.realestatefinder.data.local.FavoriteDao
import dev.patrickdorn.realestatefinder.data.local.RealEstateFinderDatabase
import dev.patrickdorn.realestatefinder.data.web.datasource.RealEstateListDataSource
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertNotNull

class OnlineOnlyRealEstateListRepositoryTest {

    private val dataSource = mockk<RealEstateListDataSource>(relaxed = true).apply {
        every { data } returns MutableStateFlow(RealEstateListData.EMPTY)
    }
    private val favoritesDao = mockk<FavoriteDao>().apply {
        every { getAll() } returns flow { listOf(Favorite(listingId = "1")) }
    }
    private val database = mockk<RealEstateFinderDatabase>().apply {
        every { favoriteDao() } returns favoritesDao
    }

    private val repository = OnlineOnlyRealEstateListRepository(dataSource, database)

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