package dev.patrickdorn.realestatefinder.core.di

import androidx.annotation.VisibleForTesting
import dev.patrickdorn.realestatefinder.core.rest.RestClient
import dev.patrickdorn.realestatefinder.data.web.datasource.RealEstateListDataSource
import dev.patrickdorn.realestatefinder.data.web.datasource.WebRealEstateListDataSource
import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import dev.patrickdorn.realestatefinder.data.web.repository.ModelMapper
import dev.patrickdorn.realestatefinder.data.web.repository.OnlineOnlyRealEstateListRepository
import dev.patrickdorn.realestatefinder.data.web.repository.RealEstateListMapper
import dev.patrickdorn.realestatefinder.data.web.repository.RealEstateListRepository
import dev.patrickdorn.realestatefinder.data.web.service.RealEstateListService
import dev.patrickdorn.realestatefinder.ui.data.RealEstateListItem
import dev.patrickdorn.realestatefinder.ui.data.RealEstateListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@VisibleForTesting
var appModule = module {
    single<RealEstateListRepository> { OnlineOnlyRealEstateListRepository(get(), get()) }
    viewModel { RealEstateListViewModel(get()) }
}

@VisibleForTesting
val dataModule = module(createdAtStart = true) {
    single<ModelMapper<RealEstateListData, List<RealEstateListItem>>> { RealEstateListMapper() }
    single<RealEstateListService> { RestClient().retrofit.create(RealEstateListService::class.java) }
    single<RealEstateListDataSource> { WebRealEstateListDataSource(get()) }
}
