package dev.patrickdorn.realestatefinder.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.patrickdorn.realestatefinder.ui.collection.RealEstateTileList
import dev.patrickdorn.realestatefinder.ui.data.RealEstateListItem
import dev.patrickdorn.realestatefinder.ui.data.RealEstateListViewModel
import dev.patrickdorn.realestatefinder.ui.theme.RealEstateFinderTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun RealEstateListScreen(
    modifier: Modifier,
    realEstateListViewModel: RealEstateListViewModel = koinViewModel(),
) {

    LaunchedEffect(key1 = "Launch", block = { realEstateListViewModel.updateRealEstateList() })

    val items: State<List<RealEstateListItem>?> =
        realEstateListViewModel.realEstateList.collectAsState(initial = emptyList())
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        RealEstateTileList(items.value, modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun RealEstateListScreenPreview() {
    RealEstateFinderTheme {
        RealEstateListScreen(Modifier)
    }
}