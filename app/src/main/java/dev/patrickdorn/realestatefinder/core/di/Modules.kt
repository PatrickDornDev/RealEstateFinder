package dev.patrickdorn.realestatefinder.core.di

import androidx.annotation.VisibleForTesting
import androidx.room.Room
import dev.patrickdorn.realestatefinder.core.rest.RestClient
import dev.patrickdorn.realestatefinder.data.local.RealEstateFinderDatabase
import dev.patrickdorn.realestatefinder.data.web.datasource.RealEstateListDataSource
import dev.patrickdorn.realestatefinder.data.web.datasource.WebRealEstateListDataSource
import dev.patrickdorn.realestatefinder.data.web.repository.OnlineOnlyRealEstateListRepository
import dev.patrickdorn.realestatefinder.data.web.repository.RealEstateListRepository
import dev.patrickdorn.realestatefinder.data.web.service.RealEstateListService
import dev.patrickdorn.realestatefinder.ui.data.RealEstateListPresenter
import dev.patrickdorn.realestatefinder.ui.data.RealEstateListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@VisibleForTesting
var appModule = module {
    single<RealEstateListRepository> { OnlineOnlyRealEstateListRepository(get(), get()) }
    single { RealEstateListPresenter(get()) }
    viewModel { RealEstateListViewModel(get(), get()) }
}

@VisibleForTesting
val dataModule = module(createdAtStart = true) {
    single<RealEstateListService> { RestClient().retrofit.create(RealEstateListService::class.java) }
    single<RealEstateListDataSource> { WebRealEstateListDataSource(get()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            RealEstateFinderDatabase::class.java,
            "db"
        ).build()
    }
}
