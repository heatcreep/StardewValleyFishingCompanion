package com.example.stardewvalleyfishingcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.example.stardewvalleyfishingcompanion.ui.theme.StardewValleyFishingCompanionTheme

class MainActivity : ComponentActivity() {

    private val fishViewModel: FishViewModel by viewModels {
        ViewModelFactory(repository = StaticJsonFishRepository(context = this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StardewValleyFishingCompanionTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    FishCardList(fishViewModel = fishViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FishCardList(fishViewModel: FishViewModel) {
    val allFish: List<FishData> by fishViewModel.allFish.observeAsState(emptyList())
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(0.dp)
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
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 0.dp).fillMaxWidth(),
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
                        painterResource(id = if(drawableId != 0) drawableId else R.drawable.all_seasons),
                        contentDescription = season,
                        modifier = Modifier.size(30.dp)
                    )

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StardewValleyFishingCompanionTheme {
        FishCard(
            fish = FishData(
                name = "Carp",
                description = "A fish",
                price = Price(
                    normal = 10,
                    silver = 20,
                    gold = 30,
                    iridium = 40
                ),
                locations = listOf("mountain lake", "town river"),
                availableSeasons = listOf("spring", "summer"),
                availableTimes = listOf("6am-11am"),
                availableWeather = listOf("any"),
                size = "3-11",
                bundles = listOf("night fishing"),
                imageUrl = "https://via.placeholder.com/300.png"
            )
        )
    }
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val repository: FishRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>)
            : T = FishViewModel(repository) as T
}

fun getSeason(season: String) {}