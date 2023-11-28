package dev.patrickdorn.realestatefinder.ui.element

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteIcon(isMarked: Boolean, onClick: () -> Unit = {}, modifier: Modifier) {
    Button(onClick = onClick, modifier = modifier.padding(16.dp)) {
        Icon(
            if (isMarked) {
                Icons.Filled.Star
            } else {
                Icons.Filled.StarBorder
            },
            null,
            modifier = modifier
                .size(24.dp),
            tint = MaterialTheme.colorScheme.surfaceTint
        )
    }
}
