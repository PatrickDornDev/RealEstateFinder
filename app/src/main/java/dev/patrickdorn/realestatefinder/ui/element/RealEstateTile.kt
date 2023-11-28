package dev.patrickdorn.realestatefinder.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.patrickdorn.realestatefinder.R
import dev.patrickdorn.realestatefinder.ui.theme.RealEstateFinderTheme

@Composable
fun RealEstateTile(
    price: String,
    title: String,
    address: String,
    url: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box(
            modifier = modifier,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_launcher_background),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Hello",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(180.dp)
                    .fillMaxWidth()
            )
            PriceLabel(
                price = price,
                modifier = modifier.align(Alignment.BottomStart)
            )
            FavoriteIcon(
                isMarked = isFavorite,
                onClick = onFavoriteClick,
                modifier = modifier
                    .align(Alignment.BottomEnd)
            )
        }
        Text(
            text = title,
            maxLines = 1,
            fontSize = 18.sp,
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .wrapContentHeight()
                .padding(start = 12.dp, end = 12.dp, top = 8.dp)
        )
        Row(
            modifier = modifier
                .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 12.dp)
        ) {
            Icon(
                Icons.Filled.LocationOn,
                null,
                modifier = modifier.padding(end = 4.dp)
            )
            Text(
                text = address,
                maxLines = 1,
                fontSize = 18.sp,
                modifier = modifier
                    .background(MaterialTheme.colorScheme.background)
                    .wrapContentHeight()
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun RealEstateTilePreview() {
    RealEstateFinderTheme {
        RealEstateTile(
            "330000 EUR",
            "Erstbezug in TOP Lage",
            "Habiskusstraße 23, 73892 Oberfeld",
            "",
            false,
            {},
            Modifier
        )
    }
}
