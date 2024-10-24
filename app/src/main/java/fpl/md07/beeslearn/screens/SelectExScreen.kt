package fpl.md07.beeslearn.screens


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.BeeAnimaComponent
import fpl.md07.beeslearn.components.TextBoxComponent

import kotlin.math.cos
import kotlin.math.sin
class SelectExScreen : ComponentActivity() { // Use ComponentActivity instead of AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelectExercise() // Call your Composable here
        }
    }
}

@Composable
fun SelectExercise() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar with hearts and coins
        TopBar()

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                // Fill the entire size of the parent
                .padding(10.dp), // Optional padding for the box
            contentAlignment = Alignment.Center // Center the content within the box
        ) {
            // Use the Bee Animation Component at the top left
            TextBoxComponent(
                modifier = Modifier
                    .fillMaxWidth() // Fill the width of the parent
                    .height(200.dp) // Set a specific height for the text box
                    .padding(top = 50.dp) // Adjust padding to avoid overlap if needed
            )
            BeeAnimaComponent(
                modifier = Modifier
                    .align(Alignment.TopEnd) // Align to top end (right corner)
                    .size(200.dp) // Increase the size of the bee image
                    .padding(top = 10.dp, start = 100.dp) // Optional padding from the top and left
            )
        }

        Spacer(modifier = Modifier.height(200.dp))

        // Center the hexagonal grid in the middle of the screen
        HexGridd()
    }
}



@Composable
fun HexGridd() {
    // Define the hexagon radius (size)
    val hexagonRadius = 40.dp

    // Scrollable row to hold hexagons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()), // Enables horizontal scrolling
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..10) {
            // Draw the hexagon with number
            HexagonWithNumber(radius = hexagonRadius, number = i)

            // Draw a connecting line (except for the last hexagon)
            if (i < 10) {
                Spacer(modifier = Modifier.width(8.dp)) // Adjust space between hexagon and line
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .width(40.dp) // Adjust width of the connecting line
                        .height(2.dp)
                        .align(Alignment.CenterVertically) // Center the line vertically with hexagons
                )
                Spacer(modifier = Modifier.width(8.dp)) // Adjust space after line
            }
        }
    }
}

@Composable
fun HexagonWithNumber(radius: Dp, number: Int) {
    // Outer and inner colors for the hexagon
    val outerColor = Color(0xFFD3D3D3)
    val innerColor = Color(0xFFC8C8C8)
    val shadowColor = Color(android.graphics.Color.parseColor("#D9D9D9"))

    Box(modifier = Modifier.size(radius * 2)) {
        Canvas(
            modifier = Modifier
                .size(radius * 2)
        ) {
            val radiusPx = radius.toPx()
            val centerX = size.width / 2
            val centerY = size.height / 2
            val angle = Math.PI / 3.0

            // Outer hexagon path
            val hexPathOuter = Path().apply {
                moveTo(
                    (centerX + radiusPx * cos(angle / 2)).toFloat(),
                    (centerY + radiusPx * sin(angle / 2)).toFloat()
                )
                for (i in 1..6) {
                    lineTo(
                        (centerX + radiusPx * cos(angle * i + angle / 2)).toFloat(),
                        (centerY + radiusPx * sin(angle * i + angle / 2)).toFloat()
                    )
                }
                close()
            }

            // Inner hexagon path
            val innerRadiusPx = radiusPx * 0.7f
            val hexPathInner = Path().apply {
                moveTo(
                    (centerX + innerRadiusPx * cos(angle / 2)).toFloat(),
                    (centerY + innerRadiusPx * sin(angle / 2)).toFloat()
                )
                for (i in 1..6) {
                    lineTo(
                        (centerX + innerRadiusPx * cos(angle * i + angle / 2)).toFloat(),
                        (centerY + innerRadiusPx * sin(angle * i + angle / 2)).toFloat()
                    )
                }
                close()
            }

            // Draw outer hexagon
            drawPath(
                path = hexPathOuter,
                color = outerColor
            )

            // Draw inner hexagon
            drawPath(
                path = hexPathInner,
                color = innerColor
            )

        }


        // Add number to the center of the hexagon
        Text(
            text = number.toString(),
            modifier = Modifier.align(Alignment.Center),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
fun PreviewSelectExercise() {
    SelectExercise()
}