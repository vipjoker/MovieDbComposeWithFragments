package com.example.moviedbtestassignment.examples.dropdown

import android.widget.Toast
import androidx.compose.foundation.excludeFromSystemGesture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.moviedbtestassignment.ButtonExample
import com.example.moviedbtestassignment.R

@Composable
@Preview(showSystemUi = true)
fun DropDownExample() {


    var showDropDown by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = "DropDownExample")
        Spacer(modifier = Modifier.height(12.dp))
        Box() {

            Button(onClick = { showDropDown = true }) {
                Text(text = stringResource(R.string.pressme))
            }

            DropdownMenu(expanded = showDropDown, onDismissRequest = { showDropDown = false },
                containerColor = Color.Red,
                offset = DpOffset(10.dp,10.dp),
                properties = PopupProperties(
                    securePolicy = SecureFlagPolicy.SecureOn// popup cannot be screenshoted
                )


            ) {
                DropdownMenuItem(
                    text = { Text("Item 1") },
                    onClick = {

                        Toast.makeText(context, "Item 1 pressed", Toast.LENGTH_SHORT).show()
                        showDropDown = false
                    },


                    )
                Divider()

                DropdownMenuItem(
                    text = { Text("Item 2") },
                    onClick = {

                        Toast.makeText(context, "Item 2 pressed", Toast.LENGTH_SHORT).show()
                        showDropDown = false
                    },
                    enabled = true,
                    leadingIcon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                    trailingIcon = { Icon(Icons.Filled.AcUnit, contentDescription = null) },
                    contentPadding = PaddingValues(20.dp),
                    colors = MenuDefaults.itemColors(
                        textColor = Color.Red,
                        trailingIconColor = Color.Green,
                        leadingIconColor = Color.Blue,
                        disabledTextColor = Color.Cyan,
                        disabledLeadingIconColor = Color.Cyan,
                        disabledTrailingIconColor = Color.Cyan

//                        indicatorColor = Color.Yellow

                    ),
                )


            }


        }
    }
}


