package com.example.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf


@Immutable
class Label(val text:String)

@Stable
class StableLabel(var text: MutableState<String> = mutableStateOf(""))