package dev.patrickdorn.realestatefinder.data.web.service

import dev.patrickdorn.realestatefinder.data.web.model.RealEstateListData
import retrofit2.http.GET

interface RealEstateListService {
    @GET("properties")
    suspend fun loadRealEstateList(): RealEstateListData

}
