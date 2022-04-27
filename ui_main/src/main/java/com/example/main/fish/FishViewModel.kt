package com.example.main.fish

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.core.fish.FishData
import com.example.core.fish.FishRepository
import javax.inject.Inject

class FishViewModel @Inject constructor(
    repository: FishRepository
) {

    private var allFish = repository.fishData
    private var initialState = mutableStateOf(allFish)
    private var currentListOfFish = mutableStateOf(allFish)

    var selectedSeasonsFilters = mutableStateOf<List<Season>>(emptyList())
    var selectedWeatherFilters = mutableStateOf<List<Weather>>(emptyList())
    var selectedLocationFilters = mutableStateOf<List<Location>>(emptyList())


    fun filteredFish(
        selectedSeasons: MutableState<List<Season>>,
        selectedWeather: MutableState<List<Weather>>,
        selectedLocations: MutableState<List<Location>>
    ): State<List<FishData>> = derivedStateOf {
        if (selectedSeasons.value.isEmpty() && selectedWeather.value.isEmpty() && selectedLocations.value.isEmpty()) {
            initialState.value
        } else {
            if (selectedSeasons.value.isNotEmpty()) {
                currentListOfFish.value = allFish.filter { fishData ->
                    fishData.availableSeasons.any { season ->
                        season in selectedSeasons.value.map {
                            it.text.lowercase()
                        }
                    }
                }
            }
            if (selectedWeather.value.isNotEmpty()) {
                currentListOfFish.value = allFish.filter { fishData ->
                    fishData.availableWeather!!.any { weather ->
                        weather in selectedWeather.value.map {
                            it.text.lowercase()
                        }
                    }
                }
            }
            if (selectedLocations.value.isNotEmpty()) {
                currentListOfFish.value = allFish.filter { fishData ->
                    fishData.locations.any { location ->
                        location in selectedLocations.value.map {
                            it.text.lowercase()
                        }
                    }
                }
            }
            currentListOfFish.value
        }
    }

}

