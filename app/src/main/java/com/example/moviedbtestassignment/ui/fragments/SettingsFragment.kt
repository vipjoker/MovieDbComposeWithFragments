package com.example.moviedbtestassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.moviedbtestassignment.ui.MoviesDbViewModel
import com.example.moviedbtestassignment.ui.theme.MovieDBTestAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {


    private val viewModel: MoviesDbViewModel by activityViewModels()

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

                    TopAppBar(title = { Text("Settings") }, navigationIcon = {
                        IconButton(onClick = {findNavController().navigateUp()}) {
                            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Info")

                        }
                    })

                }) {
                    SettingsScreen(viewModel)

                }
            }
        }
    }


        @Composable
        fun SettingsScreen(vm: MoviesDbViewModel) {
            Scaffold() {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(it)
                        .padding(16.dp)
                ) {
                    // the switch composable
                    SettingsSwitchComp(
                        name = "Dark mode",
                        // value is collected from StateFlow - updates the UI on change
                        state = vm.isDarkModeFlow().collectAsState(false)
                    ) {
                        // call ViewModel to toggle the value
                        vm.updateDarkMode(it)
                    }
                }
            }
        }

        @Composable
        fun SettingsSwitchComp(
            name: String,
            state: State<Boolean>,
            onClick: (Boolean) -> Unit
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
//                    onClick()
                    }
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.DarkMode,
                                contentDescription = "Dark mode",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = name,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.Start,
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(
                            checked = state.value,
                            onCheckedChange = { onClick(it) }
                        )
                    }
                    Divider()
                }
            }
        }
    }
