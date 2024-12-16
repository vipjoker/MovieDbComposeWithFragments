package com.example.moviedbtestassignment.ui.fragments

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviedbtestassignment.ui.MoviesDbViewModel
import com.example.moviedbtestassignment.ui.theme.MovieDBTestAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.core.NetworkClient
import com.example.core.model.domain.Character
import com.example.moviedbtestassignment.R
import com.example.moviedbtestassignment.ui.model.MovieDomain
import kotlinx.coroutines.delay

@AndroidEntryPoint
class ListFragment : Fragment() {


    private val viewModel: MoviesDbViewModel by activityViewModels()
    private val network = NetworkClient()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)

        setContent {
            val isDarkMode by viewModel.isDarkModeFlow().collectAsState(false)


            var character  by remember {
                mutableStateOf<Character?>(null)
            }

            LaunchedEffect(key1 = Unit, block = {
               delay(3000)
               network.getCharacterSafe(4)
                   .onSuccess { character = it }
            })



            MovieDBTestAssignmentTheme (
                darkTheme = isDarkMode
            ){
                Surface(
                    modifier = Modifier

                        .fillMaxSize(), color = Color.Gray
                ) {
                    Column() {

                        Text(character?.name?: "Unknown")


                        HomeScreen(viewModel, isDarkMode)

                    }
                }
            }
        }
    }


    @Composable
    private fun HomeScreen(viewModel: MoviesDbViewModel, isDarkMode: Boolean) {
        Scaffold(topBar = {

            TopAppBar(title = { Text("MoviesDb") }, actions = {

                IconButton(onClick = {
                    findNavController().navigate(R.id.action_info)
                }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Info")

                }

                IconButton(onClick = {
                    findNavController().navigate(R.id.action_settings)

                }) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                }
            })



        }){
            SearchMoviesScreen(viewModel)
        }
    }





    @Composable
    fun SearchMoviesScreen(viewModel: MoviesDbViewModel) {
        val movies by rememberUpdatedState(newValue = viewModel.uiState.collectAsLazyPagingItems())
        LaunchedEffect(true) {
            viewModel.onSearchClicked()
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box {

                Column(Modifier.fillMaxWidth()) {
                    SearchResult(movies)
                }
            }
        }
    }

    @Composable
    fun MovieItem(movie: MovieDomain,fraction:Float, callback: () -> Unit) {


        var title = movie.title
        var msg = movie.overview

        var url = "http://image.tmdb.org/t/p/w500${movie.posterPath}"
        var background = "http://image.tmdb.org/t/p/w500${movie.backdropPath}"
        var isFavorite = movie.isFavourite


        Box(modifier = Modifier
            .fillMaxWidth(fraction)
            .clickable {
                findNavController().navigate(
                    R.id.action_detail, args = bundleOf(DetailFragment.ARG_MOVIEID to movie.id)
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


    @Composable
    private fun SearchResult(movies: LazyPagingItems<MovieDomain>
    ) {




        LazyColumn (verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
            items(movies.itemCount) { index ->
                val movie = movies[index]
                if (movie != null) {


                    val configuration = LocalConfiguration.current
                    when (configuration.orientation) {
                        Configuration.ORIENTATION_LANDSCAPE -> {
                            MovieItem(
                                movie,0.5f
                            ) {
                                movies.refresh()
                            }
                        }
                        else -> {
                            MovieItem(
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


}

@Composable
fun RemoteImage(
    url: String,
    height: Dp,
    width: Dp,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    onLoadSuccess: () -> Unit = {},
    onLoadFailed: () -> Unit = {},
    onNewRequest: () -> Unit = {},
    loadingView: @Composable (() -> Unit)? = null,
    errorView: @Composable (() -> Unit)? = null,
    transformation: Transformation<Bitmap>? = null, // todo: Support multiple transformations
    colorFilter: ColorFilter? = null
) {
    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }
    val isLoading = remember { mutableStateOf(true) }
    val didFail = remember { mutableStateOf(false) }

    val heightPixel = LocalDensity.current.run { height.toPx() }.toInt()
    val widthPixel = LocalDensity.current.run { width.toPx() }.toInt()

    val glide = Glide.with(LocalContext.current.applicationContext)

    DisposableEffect(url) {
        bitmap.value = null
        onNewRequest()
        val target = object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmap.value = resource
                isLoading.value = false
                onLoadSuccess()
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                didFail.value = true
                isLoading.value = false
                onLoadFailed()
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        }
        val request = glide
            .asBitmap()
            .load(url)
            .apply(RequestOptions().override(widthPixel, heightPixel));

        if (transformation != null) {
            request.transform(transformation)
        }
        request.into(target)

        onDispose {
            glide.clear(target)
        }
    }

    Box(
        modifier = modifier
            .width(width)
            .height(height)
    ) {
        if (bitmap.value != null && !isLoading.value) {
            Image(
                modifier = Modifier
                    .width(width)
                    .height(height),
                bitmap = bitmap.value!!.asImageBitmap(),
                contentDescription = contentDescription,
                contentScale = contentScale,
                colorFilter = colorFilter
            )
        } else if (isLoading.value && loadingView != null) {
            loadingView()
        } else if (didFail.value && errorView != null) {
            errorView()
        }
    }


}


