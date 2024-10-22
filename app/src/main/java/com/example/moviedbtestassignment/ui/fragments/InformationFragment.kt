package com.example.moviedbtestassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.util.TableInfo
import com.example.moviedbtestassignment.repository.DeviceInfo
import com.example.moviedbtestassignment.ui.DeviceInfoViewModel
import com.example.moviedbtestassignment.ui.MoviesDbViewModel
import com.example.moviedbtestassignment.ui.theme.MovieDBTestAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationFragment() : Fragment() {

    private val viewModel: MoviesDbViewModel by activityViewModels()
    private val deiviceInfoViewModel: DeviceInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)

        setContent {
            val isDarkMode by viewModel.isDarkModeFlow().collectAsState(false)
            MovieDBTestAssignmentTheme(
                darkTheme = isDarkMode
            ) {
                Scaffold(topBar = {

                    TopAppBar(title = { Text("Info") }, navigationIcon = {
                        IconButton(onClick = {findNavController().navigateUp()}) {
                            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Info")
                        }
                    })

                }) {
                    val deviceInfo by deiviceInfoViewModel.deviceInfo().collectAsState(DeviceInfo("","","",""))
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(10.dp) ) {
                        Text("Device name : ${deviceInfo.deviceName}")
                        Text("Device brand : ${deviceInfo.deviceBrand}")
                        Text("Device model : ${deviceInfo.deviceModel}")


                    }

                }
            }
        }
    }
}