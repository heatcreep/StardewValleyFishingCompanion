package com.example.stardewvalleyfishingcompanion

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
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

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val repository: FishRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>)
            : T = FishViewModel(repository) as T
}

@Composable
fun FishCardList(fishViewModel: FishViewModel) {
    val allFish: List<FishData> by fishViewModel.allFish.observeAsState(emptyList())
    LazyColumn {
        items(allFish) { singleFish ->
            FishCard(fish = singleFish)
        }
    }
}

@Composable
fun FishCard(fish: FishData) {
    Row(
        modifier = Modifier.padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = rememberImagePainter(
            "https://stardewvalleywiki.com/mediawiki/images/e/e1/Albacore.png"
        )
        Image(painter = painter, contentDescription = "an image of ${fish.name}")
        Column {
            Text(
                text = fish.name,
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.secondaryVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StardewValleyFishingCompanionTheme {
        FishCard(
            fish = FishData(
                name = "Albacore",
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
                image = "https://via.placeholder.com/300.png"
            )
        )
    }
}