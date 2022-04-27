package com.example.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.TableHeader(
    text: String,
    weight: Float,
) {

    Text(
        text = text,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .weight(weight)
    )

}


@Composable
fun RowScope.TableCellWithImage(
    text: String,
    weight: Float,
    painter: Painter,
    qualityResourceId: Int? = null
) {

//    val urlPainter = rememberImagePainter(
//        data = fishResourceId,
//        builder = {
//            size(OriginalSize)
//            placeholder(R.drawable.junimo)
//            fallback(R.drawable.junimo)
//        }
//    )
    Box {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
        if (qualityResourceId != null) {
            Image(
                painter = painterResource(id = qualityResourceId),
                contentDescription = "item quality icon",
                modifier = Modifier.size(24.dp)
            )
        }

    }
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .weight(weight)
            .padding(8.dp)
    )
}