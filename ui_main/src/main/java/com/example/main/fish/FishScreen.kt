package com.example.main.fish

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.core.fish.FishData
import com.example.core.navigation.NavDelegate
import com.example.core.utils.getImageResource
import com.example.main.R
import com.example.main.fish.inject.FishScreenComponent
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun FishScreen(componentBuilder: FishScreenComponent.Builder) {
    val component = remember { componentBuilder.build() }
    val navDelegate = component.navDelegate
    val fishViewModel = component.viewModel
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    val selectedSeasons = rememberSaveable {
        fishViewModel.selectedSeasonsFilters
    }

    val selectedWeather = rememberSaveable {
        fishViewModel.selectedWeatherFilters
    }

    val selectedLocations = rememberSaveable {
        fishViewModel.selectedLocationFilters
    }
    Column {
        Box {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.junimo_md_tight_bg),
                contentDescription = "junimo background",
                contentScale = ContentScale.FillBounds
            )
            Scaffold(
                scaffoldState = scaffoldState,
                backgroundColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = { Text("Fish") },
                        actions = {
                            Button(onClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "filters "
                                )
                            }
                        }
                    )
                },
                drawerContent = {
                    FilterGroupToggle(
                        selectedSeasons = selectedSeasons,
                        selectedWeather = selectedWeather,
                        selectedLocations = selectedLocations
                    )
                },
                content = {
                    Surface(color = Color.Transparent) {
                        FishCardList(
                            fishViewModel = fishViewModel,
                            navDelegate = navDelegate,
                            selectedSeasons = selectedSeasons,
                            selectedWeather = selectedWeather,
                            selectedLocations = selectedLocations
                        )
                    }
                }
            )
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun FishCardList(
    fishViewModel: FishViewModel,
    navDelegate: NavDelegate,
    selectedSeasons: MutableState<List<Season>>,
    selectedWeather: MutableState<List<Weather>>,
    selectedLocations: MutableState<List<Location>>,
) {


    val filteredFish = fishViewModel.filteredFish(selectedSeasons, selectedWeather, selectedLocations)
    Column {
        LazyVerticalGrid(
            cells = GridCells.Fixed(integerResource(id = R.integer.grid_span_count)),
        ) {
            items(filteredFish.value) { singleFish ->
                FishCard(
                    fish = singleFish,
                    navDelegate = navDelegate
                )
            }


        }
    }

}

@ExperimentalMaterialApi
@Composable
fun FishCard(fish: FishData, navDelegate: NavDelegate) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .height(150.dp),
        backgroundColor = Color.White,
        border = BorderStroke(2.dp, Color.Black),
        onClick = {
            navDelegate.goToFishDetails(fish.id)
        }


    ) {
        Column {
            val fishImage = getImageResource(fish.image)
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 0.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = fish.name,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.secondaryVariant
                )
                Image(
                    painter = rememberImagePainter(data = fishImage),
                    contentDescription = "an image of ${fish.name}",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(48.dp)
                )
            }
        }
    }
}

@Composable
fun FilterGroupToggle(
    selectedSeasons: MutableState<List<Season>>,
    selectedWeather: MutableState<List<Weather>>,
    selectedLocations: MutableState<List<Location>>
) {
    val seasonOptions: List<Season> = listOf(
        Season.SPRING,
        Season.SUMMER,
        Season.FALL,
        Season.WINTER
    )

    val weatherOptions: List<Weather> = listOf(
        Weather.RAIN
    )

    val locationOptions: List<Location> = listOf(
        Location.FOREST_POND,
        Location.FOREST_RIVER,
        Location.TOWN_RIVER,
        Location.MOUNTAIN_LAKE,
        Location.OCEAN,
        Location.GINGER_ISLAND_OCEAN,
        Location.GINGER_ISLAND_POND,
        Location.GINGER_ISLAND_RIVER,
        Location.MINES_20,
        Location.MINES_60,
        Location.MINES_100,
        Location.SECRET_WOODS_POND,
        Location.CINDERSNAP_FOREST_POND,
        Location.MUTANT_BUG_LAIR,
        Location.NIGHT_MARKET,
        Location.PIRATE_COVE,
        Location.SEWERS,
        Location.VOLCANO_CALDERA,
        Location.WITCHES_SWAMP
    )

    val onSeasonFilterSelect = { season: Season ->
        if (selectedSeasons.value.contains(season)) {
            selectedSeasons.value = selectedSeasons.value.minus(season)
        } else {
            selectedSeasons.value = selectedSeasons.value.plus(season)
        }
        println("FILTERS: $selectedSeasons")
    }

    val onWeatherFilterSelect = { weather: Weather ->
        if (selectedWeather.value.contains(weather)) {
            selectedWeather.value = selectedWeather.value.minus(weather)
        } else {
            selectedWeather.value = selectedWeather.value.plus(weather)
        }
    }

    val onLocationFilterSelect = { location: Location ->
        if(selectedLocations.value.contains(location)) {
            selectedLocations.value = selectedLocations.value.minus(location)
        } else {
            selectedLocations.value = selectedLocations.value.plus(location)
        }
    }

    Column(modifier = Modifier.padding(10.dp)) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Text("By Season:")
            FlowRow(
                mainAxisAlignment = MainAxisAlignment.Start,
                mainAxisSize = SizeMode.Expand,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                seasonOptions.forEach { option ->
                    Button(
                        onClick = { onSeasonFilterSelect(option) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selectedSeasons.value.contains(option)) {
                                MaterialTheme.colors.primary
                            } else {
                                Color.White
                            }
                        ),
                        modifier = Modifier.padding(horizontal = 5.dp)
                    ) {
                        Text(text = option.text)
                    }
                }
            }
            Text("By Weather:")
            FlowRow(
                mainAxisAlignment = MainAxisAlignment.Start,
                mainAxisSize = SizeMode.Expand,
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(ScrollState(0))
            ) {
                weatherOptions.forEach { option ->
                    Button(
                        onClick = { onWeatherFilterSelect(option) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selectedWeather.value.contains(option)) {
                                MaterialTheme.colors.primary
                            } else {
                                Color.White
                            }
                        ),
                        modifier = Modifier.padding(horizontal = 5.dp)
                    ) {
                        Text(text = option.text)
                    }
                }
            }
            Text("By Location:")
            FlowRow(
                mainAxisAlignment = MainAxisAlignment.Start,
                mainAxisSize = SizeMode.Expand,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                locationOptions.forEach { option ->
                    Button(
                        onClick = { onLocationFilterSelect(option) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selectedLocations.value.contains(option)) {
                                MaterialTheme.colors.primary
                            } else {
                                Color.White
                            }
                        ),
                        modifier = Modifier.padding(horizontal = 5.dp)
                    ) {
                        Text(text = option.text)
                    }
                }
            }
        }

    }
}

enum class Season(val text: String) {
    SPRING("Spring"), SUMMER("Summer"), FALL("Fall"), WINTER("Winter")
}

enum class Weather(val text: String) {
    RAIN("Rain")
}

enum class Location(val text: String) {
    TOWN_RIVER("Town River"),
    FOREST_RIVER("Forest River"),
    FOREST_POND("Forest Pond"),
    OCEAN("Ocean"),
    MOUNTAIN_LAKE("Mountain Lake"),
    GINGER_ISLAND_POND("Ginger Island Pond"),
    GINGER_ISLAND_OCEAN("Ginger Island Ocean"),
    GINGER_ISLAND_RIVER("Ginger Island River"),
    MINES_20("Mines (level 20)"),
    MINES_60("Mines (level 60)"),
    MINES_100("Mines (level 100)"),
    SECRET_WOODS_POND("Secret Woods Pond"),
    VOLCANO_CALDERA("Volcano Caldera"),
    SEWERS("The Sewers"),
    MUTANT_BUG_LAIR("Mutant Bug Lair"),
    PIRATE_COVE("Pirate Cove"),
    WITCHES_SWAMP("Witch's Swamp"),
    CINDERSNAP_FOREST_POND("Cindersnap Forest Pond"),
    NIGHT_MARKET("Night Market")
}