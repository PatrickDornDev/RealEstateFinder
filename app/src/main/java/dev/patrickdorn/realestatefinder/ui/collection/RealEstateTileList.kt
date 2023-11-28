package dev.patrickdorn.realestatefinder.ui.collection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.patrickdorn.realestatefinder.ui.data.RealEstateListItem
import dev.patrickdorn.realestatefinder.ui.element.RealEstateTile
import dev.patrickdorn.realestatefinder.ui.theme.RealEstateFinderTheme

@Composable
fun RealEstateTileList(realEstateListItems: List<RealEstateListItem>?, modifier: Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        realEstateListItems?.forEach {
            item {
                RealEstateTile(it.price, it.title, it.address, it.url)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RealEstateTileListPreview() {
    RealEstateFinderTheme {
        RealEstateTileList(List(30) {
            RealEstateListItem(
                it.toString(),
                it.toString() + "€",
                "Item # $it",
                "House with Number: $it",
                ""
            )
        }, Modifier)
    }
}