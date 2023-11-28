package dev.patrickdorn.realestatefinder.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import dev.patrickdorn.realestatefinder.ui.screen.RealEstateListScreen
import dev.patrickdorn.realestatefinder.ui.theme.RealEstateFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RealEstateFinderTheme {
                RealEstateListScreen(modifier = Modifier)
            }
        }
    }
}
