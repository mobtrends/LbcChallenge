package com.example.lbcchallenge.presentation

import android.widget.ViewFlipper

enum class StateChild { LOADING, CONTENT, ERROR }

var ViewFlipper?.state: StateChild
    get() = StateChild.values()[this?.displayedChild ?: 0]
    set(state) {
        if (this?.displayedChild != state.ordinal) {
            this?.displayedChild = state.ordinal
        }
    }