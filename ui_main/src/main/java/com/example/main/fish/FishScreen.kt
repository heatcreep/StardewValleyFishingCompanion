package com.example.main.fish

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.core.fish.FishData
import com.example.core.navigation.NavDelegate
import com.example.main.R
import com.example.main.fish.inject.FishScreenComponent


@Composable
fun FishScreen(componentBuilder: FishScreenComponent.Builder) {
    val component = remember { componentBuilder.build() }
    val navDelegate = component.navDelegate
    val fishViewModel = component.viewModel
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.junimo_md_tight_bg),
            contentDescription = "junimo background",
            contentScale = ContentScale.FillBounds
        )
        Scaffold(
            backgroundColor = Color.Transparent,
            content = {
                Surface(color = Color.Transparent) {
                    FishCardList(fishViewModel = fishViewModel, navDelegate = navDelegate)
                }
            }
        )
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FishCardList(fishViewModel: FishViewModel, navDelegate: NavDelegate) {
    val allFish: List<FishData> by fishViewModel.allFish.observeAsState(emptyList())
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
    ) {
        items(allFish) { singleFish ->
            FishCard(fish = singleFish, navDelegate)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FishCard(fish: FishData, navDelegate: NavDelegate) {
    Card(
        modifier = Modifier.padding(5.dp).height(150.dp),
        backgroundColor = Color.White,
        border = BorderStroke(2.dp, Color.Black),
        onClick = {
            navDelegate.goToFishDetails(fish.id)
        }


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
                    painter = painter,
                    contentDescription = "an image of ${fish.name}",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(48.dp)
                )
            }
        }
    }
}