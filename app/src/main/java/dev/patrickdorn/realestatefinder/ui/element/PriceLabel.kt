package dev.patrickdorn.realestatefinder.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun PriceLabel(price: String, modifier: Modifier) =
    Text(
        text = price,
        modifier = modifier
            .padding(bottom = 24.dp)
            .shadow(12.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(MaterialTheme.colorScheme.background)
            .wrapContentHeight()
            .padding(horizontal = 12.dp, vertical = 4.dp)
    )
