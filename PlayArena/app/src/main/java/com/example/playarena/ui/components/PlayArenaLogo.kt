package com.example.playarena.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.animation.core.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlayArenaLogo() {

    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = 1000
        ),
        label = ""
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.scale(scale)
    ) {

        Icon(
            imageVector = Icons.Default.SportsEsports,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = buildAnnotatedString {

                withStyle(
                    SpanStyle(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Pl")
                }

                withStyle(
                    SpanStyle(
                        color = Color(0xFFFFC107),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("ay")
                }

                withStyle(
                    SpanStyle(
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Are")
                }

                withStyle(
                    SpanStyle(
                        color = Color(0xFF2196F3),
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("na")
                }
            },
            fontSize = 40.sp
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Play More • Earn More • Be a Champion",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}