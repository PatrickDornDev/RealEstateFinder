package dev.patrickdorn.realestatefinder.ui.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.patrickdorn.realestatefinder.data.web.repository.RealEstateListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RealEstateListViewModel(
    private val realEstateListRepository: RealEstateListRepository,
    presenter: RealEstateListPresenter
) : ViewModel() {

    val realEstateList = presenter.realEstateList.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

    fun updateRealEstateList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                realEstateListRepository.updateRealEstateList()
            } catch (e: Exception) {
                //TODO Use Timber or similar "framework"
                //TODO Handle error gracefully
                Log.e(this.javaClass.name, e.message ?: "")
            }
        }
    }

    fun toggleFavorite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            realEstateListRepository.toggleFavorite(id)
        }
    }
}