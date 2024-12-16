package com.example.moviedbtestassignment.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.example.compose.AppTheme
import com.example.moviedbtestassignment.R
import com.example.moviedbtestassignment.ui.MoviesDbViewModel
import com.example.moviedbtestassignment.ui.fragments.DetailFragment.Companion.ARG_MOVIEID
import com.example.moviedbtestassignment.ui.fragments.RemoteImage
import com.example.moviedbtestassignment.ui.model.MovieDomain
import androidx.compose.material3.Scaffold as Scaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OldDetailScreen(movieId:Int, nav: NavHostController, viewModel: MoviesDbViewModel = hiltViewModel()) {







    val isDarkMode by viewModel.isDarkModeFlow().collectAsState(false)
    val movie by viewModel.findOneMovieById(movieId)
        .collectAsState(MovieDomain(0, false, "", listOf(), "", "", "", 0.0, "", "", "", false, 0.0, 0, false))

    AppTheme(
        darkTheme = isDarkMode
    ) {
        Scaffold(topBar = {
            TopAppBar(title = { Text("Details") }, navigationIcon = {

                IconButton(onClick = {
                    nav.navigateUp()

                }) {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Info")
                }
            })

        }) {

//                    DetailView(movie)

            MovieInfo(movie,it)

        }
    }








    }




    @Composable
    fun DetailView(movie: MovieDomain, viewModel: MoviesDbViewModel) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            var title = movie.title
            var msg = movie.overview

            var url = "http://image.tmdb.org/t/p/w500${movie.posterPath}"
            var background = "http://image.tmdb.org/t/p/w500${movie.backdropPath}"
            var isFavorite = movie.isFavourite
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                RemoteImage(

                    background, 200.dp, 500.dp, contentScale = ContentScale.FillWidth

                )
            }
            Row() {
                RemoteImage(url, 140.dp, 80.dp, contentScale = ContentScale.FillBounds)

                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20f.sp,
                            color = Color.Black
                        )
                        Image(
                            painterResource(if (isFavorite) R.drawable.favorite else R.drawable.non_favourite),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(7.dp)
                                .clickable {
                                    viewModel.toggleFavourite(movie)
                                })
                    }


                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(text = "Rating :")
                        Text(text = "${movie.voteAverage}")

                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(text = "Release date :")
                        Text(text = "${movie.releaseDate}")

                    }

                }
            }

            Text(text = msg, color = Color.Black, modifier = Modifier.padding(10.dp))
        }

    }
