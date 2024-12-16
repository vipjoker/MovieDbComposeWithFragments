package com.example.moviedbtestassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.compose.AppTheme
import com.example.moviedbtestassignment.R
import com.example.moviedbtestassignment.ui.MoviesDbViewModel
import com.example.moviedbtestassignment.ui.model.MovieDomain
import com.example.moviedbtestassignment.ui.screen.MovieInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {


    private val viewModel: MoviesDbViewModel by activityViewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)

        setContent {

            val isDarkMode by viewModel.isDarkModeFlow().collectAsState(false)
            val movie by viewModel.findOneMovieById(arguments?.getInt(ARG_MOVIEID) ?: 0)
                .collectAsState(MovieDomain(0, false, "", listOf(), "", "", "", 0.0, "", "", "", false, 0.0, 0, false))

            AppTheme (
                darkTheme = isDarkMode
            ) {
                Scaffold(topBar = {
                    TopAppBar(title = { Text("Details") }, navigationIcon = {
                        IconButton(onClick = { findNavController().navigateUp() }) {
                            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Info")
                        }
                    })

                }) {

//                    DetailView(movie)

                    MovieInfo(movie, it)

                }
            }
        }
    }


    @Composable
    fun DetailView(movie: MovieDomain) {
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
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
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
                        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20f.sp, color = Color.Black)
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

    companion object {
        const val ARG_MOVIEID = "arg_movie_id"
    }
}


