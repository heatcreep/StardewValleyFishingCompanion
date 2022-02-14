package com.example.main.fish

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.example.core.fish.FishData
import com.example.main.R
import com.example.main.fish.inject.FishScreenComponent


@Composable
fun FishScreen(componentBuilder: FishScreenComponent.Builder) {
    val component = remember { componentBuilder.build() }
    val fishViewModel = component.viewModel
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fish") }
            )
        },
        content = {
            Surface(color = MaterialTheme.colors.background) {
                FishCardList(fishViewModel = fishViewModel)
            }
        }
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FishCardList(fishViewModel: FishViewModel) {
    val allFish: List<FishData> by fishViewModel.allFish.observeAsState(emptyList())
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(6.dp)
    ) {
        items(allFish) { singleFish ->
            FishCard(fish = singleFish)
        }
    }
}

@Composable
fun FishCard(fish: FishData) {
    Card(
        modifier = Modifier.padding(4.dp),
        backgroundColor = Color.LightGray

    ) {
        Column(
        ) {
            val painter = rememberImagePainter(
                data = fish.imageUrl,
                builder = {
                    placeholder(R.drawable.junimo)
                    fallback(R.drawable.junimo)
                }
            )
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 0.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = fish.name,
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.button,
                    color = MaterialTheme.colors.secondaryVariant
                )
                Image(
                    painter = painter,
                    contentDescription = "an image of ${fish.name}",
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .size(50.dp)
                )
            }
            Row(
                modifier = Modifier.padding(top = 20.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                val context = LocalContext.current
                fish.availableSeasons.forEach { season ->
                    val drawableId = remember(season) {
                        context.resources.getIdentifier(
                            season,
                            "drawable",
                            context.packageName
                        )
                    }
                    Image(
                        painterResource(id = if (drawableId != 0) drawableId else R.drawable.all_seasons),
                        contentDescription = season,
                        modifier = Modifier.size(30.dp)
                    )

                }
            }
        }
    }
}