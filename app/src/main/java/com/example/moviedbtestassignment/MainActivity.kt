package com.example.moviedbtestassignment

import android.os.Bundle
import androidx.activity.viewModels

import androidx.fragment.app.FragmentActivity
import com.example.moviedbtestassignment.ui.MoviesDbViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {


    private val viewModel: MoviesDbViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.content_main)
//            ComposeView(this).apply {
//                consumeWindowInsets = false
//                setContent {
//
////                    AndroidViewBinding(ContentMainBinding::inflate)
//
//
//                }


//        setContent {
//            MovieDBTestAssignmentTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
//                    Column {
//
//
//                        MessageCard("Test title","test card")
//                        Button(onClick = {
//
//                            viewModel.onSearchClicked()
//                            Log.i("DEBUG8", "onCreate: ")}){
//                            Text("Press me")
//                        }
//
//
//
//                        SearchMoviesScreen(viewModel)                    }
//                }
//            }
//        }
//            })
    }


}