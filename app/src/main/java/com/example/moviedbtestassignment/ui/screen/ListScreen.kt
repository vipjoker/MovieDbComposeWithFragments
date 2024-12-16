package com.example.moviedbtestassignment.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.compose.AppTheme
import com.example.moviedbtestassignment.R
import com.example.moviedbtestassignment.ui.MoviesDbViewModel
import com.example.moviedbtestassignment.ui.fragments.DetailFragment
import com.example.moviedbtestassignment.ui.fragments.RemoteImage
import com.example.moviedbtestassignment.ui.model.MovieDomain


@Composable
fun ListScreen(nav: NavHostController, viewModel: MoviesDbViewModel = hiltViewModel()){


    val isDarkMode by viewModel.isDarkModeFlow().collectAsState(false)





//    var character  by remember {
//        mutableStateOf<Character?>(null)
//    }
//
//    LaunchedEffect(key1 = Unit, block = {
//        delay(3000)
//        network.getCharacterSafe(4)
//            .onSuccess { character = it }
//    })



    AppTheme (
        darkTheme = isDarkMode
    ){
        Surface(
            modifier = Modifier

                .fillMaxSize(), color = Color.Gray
        ) {
            Column() {

//                Text(character?.name?: "Unknown")


                HomeScreen(nav,viewModel, isDarkMode)

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(nav:NavHostController,viewModel: MoviesDbViewModel, isDarkMode: Boolean) {
    Scaffold(topBar = {

        TopAppBar(title = { Text("MoviesDb") }, actions = {

            IconButton(onClick = {
//                findNavController().navigate(R.id.action_info)
            }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "Info")

            }

            IconButton(onClick = {
//                findNavController().navigate(R.id.action_settings)

            }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
            }
        })



    }){padding->
        SearchMoviesScreen(nav,viewModel, padding)
    }
}

@Composable
fun SearchMoviesScreen(nav: NavHostController,viewModel: MoviesDbViewModel = hiltViewModel(), padding: PaddingValues) {
    val movies by rememberUpdatedState(newValue = viewModel.uiState.collectAsLazyPagingItems())
    LaunchedEffect(true) {
        viewModel.onSearchClicked()
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {

            Column(Modifier.fillMaxWidth()) {
                SearchResult(nav,movies,viewModel)
            }
        }
    }
}


@Composable
private fun SearchResult(nav: NavHostController,movies: LazyPagingItems<MovieDomain>,
                         viewModel: MoviesDbViewModel
) {




    LazyColumn (verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
        items(movies.itemCount) { index ->
            val movie = movies[index]
            if (movie != null) {


                val configuration = LocalConfiguration.current
                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        MovieItem(nav,viewModel,
                            movie,0.5f
                        ) {
                            movies.refresh()
                        }
                    }
                    else -> {
                        MovieItem(nav,
                            viewModel,
                            movie, 1f
                        ) {
                            movies.refresh()
                        }
                    }
                }





            }
        }
    }
}


@Composable
fun MovieItem(nav: NavHostController,viewModel: MoviesDbViewModel,movie: MovieDomain,fraction:Float, callback: () -> Unit) {


    var title = movie.title
    var msg = movie.overview

    var url = "http://image.tmdb.org/t/p/w500${movie.posterPath}"
    var background = "http://image.tmdb.org/t/p/w500${movie.backdropPath}"
    var isFavorite = movie.isFavourite


    Box(modifier = Modifier
        .fillMaxWidth(fraction)
        .clickable {
            nav.navigate("oldetail/${movie.id}"
//                R.id.action_detail, args = bundleOf(DetailFragment.ARG_MOVIEID to movie.id)
            )
        }
    ) {
        RemoteImage(
            background, 140.dp, 400.dp, contentScale = ContentScale.FillWidth, modifier = Modifier
                .fillMaxSize()
        )

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier =
            Modifier
                .fillMaxWidth()
                .height(Dp(140f))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0, 0, 0, 0),
                            Color(0, 0, 0, 120)
                        )
                    )
                )
        ) {
            RemoteImage(url, 140.dp, 80.dp, contentScale = ContentScale.FillBounds)


            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {

                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20f.sp, color = Color.White)
                Text(text = msg, color = Color.White, maxLines = 2)

            }


        }


        Image(
            painterResource(if (isFavorite) R.drawable.favorite else R.drawable.non_favourite),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(42.dp)
                .align(Alignment.TopEnd)
                .padding(5.dp)
                .clickable {
                    callback()
                    viewModel.toggleFavourite(movie)

                }

        )
    }

}


