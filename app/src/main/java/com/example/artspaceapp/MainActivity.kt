package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyGallery()
                }
            }
        }
    }
}

@Composable
fun MyGallery() {
    var currentPage: Int by remember {
        mutableIntStateOf(0)
    }

    val (currentPainterId: Int, currentSourceId: Int, currentArtistId: Int, currentArtTitleId: Int, currentArtYearId: Int) = when (currentPage) {
        0 -> {
            listOf(
                R.drawable.castle,
                R.string.castle_artist,
                R.string.castle_source,
                R.string.castle,
                R.string.castle_year
            )
        }


        else -> {
                listOf(
                    R.drawable.forest,
                    R.string.forest_artist,
                    R.string.forest_source,
                    R.string.forest,
                    R.string.forest_year
                )
            }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        MyPicture(
            painterId = currentPainterId,
            describeId = currentArtTitleId,
            sourceId = currentSourceId,
            modifier = Modifier.weight(0.7f)
        )
        PictureArtistAndName(
            artTitleId = currentArtTitleId,
            artistNameId = currentArtistId,
            artYearId = currentArtYearId,
            modifier = Modifier.weight(0.2f)
        )
        NextAndPreviousButton(
            onNextClick = {
                currentPage = currentPageUpdate(isNext = true, currentPage)
            },
            onPreviousClick = {
                currentPage = currentPageUpdate(isNext = false, currentPage)
            },
            modifier = Modifier.weight(0.1f)
        )
    }
}

@VisibleForTesting
internal fun currentPageUpdate(isNext: Boolean, currentPage: Int): Int {
    var newCurrentPage = currentPage
    if (isNext) {
        newCurrentPage++
        newCurrentPage %= 3
    } else {
        if (currentPage == 0) {
            newCurrentPage = 2
        } else {
            newCurrentPage--
            newCurrentPage %= 3
        }
    }
    return newCurrentPage
}

@Composable
fun MyPicture(
    @DrawableRes painterId: Int,
    @StringRes describeId: Int,
    @StringRes sourceId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 20.dp, end = 20.dp)
    ) {
        Image(
            painter = painterResource(id = painterId),
            contentDescription = stringResource(id = describeId),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth()
                .weight(0.9f)
                .border(width = 3.dp, color = Color.LightGray)
                .shadow(elevation = 10.dp)
                .background(Color.White)
        )
    }
}

@Composable
fun PictureArtistAndName(
    @StringRes artTitleId: Int,
    @StringRes artistNameId: Int,
    @StringRes artYearId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp)
            .background(Color.LightGray)
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(id = artTitleId),
            fontSize = 28.sp
        )

        Row {
            Text(
                text = stringResource(id = artistNameId),
                fontWeight = FontWeight.Bold,
            )
            Text(text = " (" + stringResource(id = artYearId) + ")")
        }

    }
}


@Composable
fun NextAndPreviousButton(
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(20.dp)
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.weight(0.5f)
        ) {
            Text(
                text = stringResource(R.string.previous)
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(0.5f)
        ) {
            Text(
                text = stringResource(R.string.next)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        MyGallery()
    }
}

