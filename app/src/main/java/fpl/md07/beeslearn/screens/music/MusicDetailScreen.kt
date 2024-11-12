package fpl.md07.beeslearn.screens.music

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.models.Music2
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.data.music2List

@Composable
fun MusicDetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(R.drawable.back_left),
            contentDescription = "Back",
            modifier = Modifier
                .padding(16.dp)
                .size(30.dp)
                .clickable { navController.popBackStack() }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .size(width = 320.dp, height = 190.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.music1),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .fillMaxSize()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 15.dp, top = 18.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(R.drawable.play),
                contentDescription = "Play",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(35.dp),
            )
            Icon(
                painter = painterResource(R.drawable.heart1),
                contentDescription = "Play",
                modifier = Modifier
                    .padding(end = 10.dp, start = 10.dp)
                    .size(35.dp)
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(music2List) { music2 ->
                Music2Item(music2, navController)
            }
        }
    }
}

@Composable
fun Music2Item (music2: Music2, navController: NavController) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 10.dp)
            .clickable {navController.navigate("musicScreen3")},
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = music2.number.toString(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Nunito_Bold,
            modifier = Modifier
                .padding(end = 30.dp)
        )
        Column (
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = music2.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Nunito_Bold,
                color = colorResource(id = R.color.secondary_color),
            )
            Text(
                text = music2.content,
                fontSize = 18.sp,
                color = colorResource(id = R.color.secondary_color),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMusicScreen2() {
    var navController = rememberNavController()
    MusicDetailScreen(navController)
}