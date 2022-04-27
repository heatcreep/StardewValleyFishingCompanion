package com.example.ui_fishdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.core.components.TableCellWithImage
import com.example.core.components.TableHeader
import com.example.core.fish.FishData
import com.example.core.utils.getImageResource
import com.example.ui_fishdetails.inject.FishDetailComponent

@ExperimentalFoundationApi
@Composable
fun FishDetailScreen(componentBuilder: FishDetailComponent.Builder) {
    val component = remember { componentBuilder.build() }
    val fishDetailViewModel = component.viewModel
    val goBack = { component.navDelegate.goBack() }
    val fish: FishData by fishDetailViewModel.fishDetail.observeAsState(FishData())
    val fishImage = getImageResource(fish.image)
    val painter = rememberImagePainter(data = fishImage)
    Surface {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.junimo_md_tight_bg),
            contentDescription = "junimo background",
            contentScale = ContentScale.FillBounds
        )
        Card(

            modifier = Modifier
                .padding(32.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    val painter = rememberImagePainter(
//                        data = fish.imageUrl,
//                        builder = {
//                            placeholder(R.drawable.junimo)
//                            fallback(R.drawable.junimo)
//                        }
//                    )

                    Text(
                        text = fish.name,
                        fontSize = 48.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                    Image(
                        painter = painter,
                        contentDescription = "an image of ${fish.name}",
                        modifier = Modifier
                            .padding(top = 48.dp)
                            .size(90.dp)
                    )
                    Text(
                        text = fish.description,
                        modifier = Modifier.padding(0.dp, 10.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                }
                Divider()
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_wb_cloudy_24),
                            contentDescription = "cloud icon",
                            modifier = Modifier
                                .size(32.dp)
                                .padding(end = 8.dp)
                        )
                        Text(text = "Weather", style = MaterialTheme.typography.h5)
                    }
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(3),
                        modifier = Modifier
                            .padding(start = 32.dp)
                    ) {
                        items(fish.availableWeather!!) { weather ->
                            Text(
                                text = weather,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                Divider()
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icons8_coins_48),
                            contentDescription = "cloud icon",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(32.dp)
                                .padding(end = 8.dp)
                        )
                        Text(text = "Sell Prices", style = MaterialTheme.typography.h5)
                    }
                    FishPriceTable(fish = fish, painter = painter)
                }
            }
        }


    }
}

@ExperimentalFoundationApi
@Composable
fun FishPriceTable(fish: FishData, painter: Painter) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val columnWeight = .3f
            val price = fish.price

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TableHeader(text = "Base", weight = columnWeight)
                    TableHeader(text = "Fisher Profession", weight = columnWeight)
                    TableHeader(text = "Angler Profession", weight = columnWeight)
                }
            }
            item {
                FishPriceRow(price = price.regular, painter = painter)
            }
            item {
                FishPriceRow(
                    price = price.silver,
                    painter = painter,
                    qualityResourceId = R.drawable.silver_quality_icon
                )
            }
            item {
                FishPriceRow(
                    price = price.gold,
                    painter = painter,
                    qualityResourceId = R.drawable.gold_quality_icon
                )
            }
            item {
                FishPriceRow(
                    price = price.iridium,
                    painter = painter,
                    qualityResourceId = R.drawable.iridium_quality_icon
                )
            }
        }
    }
}

@Composable
fun FishPriceRow(
    price: Int,
    painter: Painter,
    qualityResourceId: Int? = null

) {
    val columnWeight = .33f
    val fisherProfMultiplier = 0.25
    val anglerProfMultiplier = 0.50

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        TableCellWithImage(
            text = sellPrice(price),
            weight = columnWeight,
            painter = painter,
            qualityResourceId = qualityResourceId
        )
        TableCellWithImage(
            text = sellPrice(price, fisherProfMultiplier),
            weight = columnWeight,
            painter = painter,
            qualityResourceId = qualityResourceId
        )
        TableCellWithImage(
            text = sellPrice(price, anglerProfMultiplier),
            weight = columnWeight,
            painter = painter,
            qualityResourceId = qualityResourceId
        )
    }
}

fun sellPrice(price: Int, multiplier: Double = 0.0): String {
    return "${(price + (price * multiplier)).toInt()}g"
}
