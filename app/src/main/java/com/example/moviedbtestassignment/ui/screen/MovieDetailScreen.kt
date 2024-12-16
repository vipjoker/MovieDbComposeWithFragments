package com.example.moviedbtestassignment.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.compose.AppTheme
import com.example.moviedbtestassignment.components.common.DataPoint
import com.example.moviedbtestassignment.components.common.DataPointComponent
import com.example.moviedbtestassignment.ui.MoviesDbViewModel
import com.example.moviedbtestassignment.ui.model.MovieDomain



@Composable
fun MovieDetailScreen(viewModel: MoviesDbViewModel,movieId:Int){


    val movie by viewModel.findOneMovieById(movieId).collectAsState(null)


    MovieInfo(movie, PaddingValues(0.dp))

}

@Composable
fun MovieInfo(movieDomain: MovieDomain?, paddingValues: PaddingValues){
    if(movieDomain == null){
        LoadingState()
        return
    }


    val characterDataPoints by  remember {
        derivedStateOf() {

            buildList {

                add(DataPoint(title = "Title: ", description = movieDomain.title))
                add(DataPoint(title = "Overview: ", description = movieDomain.overview))
                add(DataPoint(title = "Release date: ", description = movieDomain.releaseDate))
                add(DataPoint(title = "Original title: ", description = movieDomain.originalTitle))

            }


        }
    }


    LazyColumn (modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(all = 16.dp)){
        if(movieDomain == null){
            item { LoadingState() }
            return@LazyColumn
        }

        item{
            SubcomposeAsyncImage(model = "http://image.tmdb.org/t/p/w500${movieDomain.backdropPath}",
                contentDescription = "Image",

                modifier = Modifier.fillMaxWidth().background(Color.Cyan)

                    .aspectRatio(16.0f/7.0f),
                contentScale = ContentScale.FillWidth,
                loading = { LoadingState() }


            )
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }

        items(characterDataPoints){
            Spacer(modifier = Modifier.height(32.dp))
            DataPointComponent(it)
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }

    }


    
}


@Preview
@Composable
fun PreviewMovieInfo(){
    MovieInfo(
        MovieDomain(
            0, false, "", listOf<Int>(),
            "", "", "Overiview of movie", 30.0, "", "", "Simple title", false, 3.0, 3, false
        ), PaddingValues(0.dp)
    )
}
@Preview
@Composable
fun PreviewEmptyMovieInfo(){
    MovieInfo(null, PaddingValues(0.dp))
}




@Composable
private fun LoadingState(){

    CircularProgressIndicator(modifier = Modifier
        .fillMaxSize()
        .padding(all = 128.dp),
        color = Color.Cyan
    )


}

