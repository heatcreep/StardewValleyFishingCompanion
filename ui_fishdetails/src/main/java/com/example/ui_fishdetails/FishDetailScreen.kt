package com.example.ui_fishdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.core.fish.FishData
import com.example.ui_fishdetails.inject.FishDetailComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FishDetailScreen(componentBuilder: FishDetailComponent.Builder) {
    val component = remember { componentBuilder.build() }
    val fishDetailViewModel = component.viewModel
    val goBack = { component.navDelegate.goBack() }
    val fish: FishData by fishDetailViewModel.fishDetail.observeAsState(FishData())
    Surface {
        Column {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val painter = rememberImagePainter(
                    data = fish.imageUrl,
                    builder = {
                        placeholder(R.drawable.junimo)
                        fallback(R.drawable.junimo)
                    }
                )
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
                Divider()
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_wb_cloudy_24),
                        contentDescription = "cloud icon",
                        modifier = Modifier.size(32.dp).padding(end = 8.dp)
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
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Text(text = "Base")
                    Text(text = "Fisher Profession")
                    Text(text = "Angler Profession")
                }
            }
        }

    }
}
