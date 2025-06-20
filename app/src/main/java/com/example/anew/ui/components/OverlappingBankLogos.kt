package com.example.anew.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun OverlappingBankLogos(
    bankLogoResIds: List<Int>,
    maxVisible: Int = 4,
    modifier: Modifier = Modifier,
    circleSize: Int = 40,
    overlapOffset: Int = 12,
    extraCountTextColor: Color = Color.White,
    extraCountBackground: Color = MaterialTheme.colorScheme.primary
) {
    val visibleLogos = bankLogoResIds.take(maxVisible)
    val extraCount = bankLogoResIds.size - maxVisible

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy((-overlapOffset).dp)) {
        visibleLogos.forEachIndexed { index, resId ->
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(circleSize.dp)
                    .zIndex((maxVisible - index).toFloat()),
                tonalElevation = 2.dp
            ) {
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = "Bank logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
        }
        if (extraCount > 0) {
            Surface(
                shape = CircleShape,
                color = extraCountBackground,
                modifier = Modifier
                    .size(circleSize.dp)
                    .zIndex(0f),
                tonalElevation = 2.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "+$extraCount",
                        color = extraCountTextColor,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}
