package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val CHEATER_TEXT = "CHEATER_TEXT"

class CheatViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var cheaterText: Int?
        get() = savedStateHandle[CHEATER_TEXT]
        set(value) = savedStateHandle.set(CHEATER_TEXT, value)
}