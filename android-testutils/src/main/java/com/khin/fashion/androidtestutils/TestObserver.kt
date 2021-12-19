package com.khin.fashion.androidtestutils

import androidx.lifecycle.Observer
import org.junit.Assert

class TestObserver<T> : Observer<T> {

    private val mutableValues = mutableListOf<T>()

    val values: List<T> = mutableValues

    override fun onChanged(t: T) {
        mutableValues.add(t)
    }

    fun assertViewState(initialState: T, vararg nextStateReducers: T.() -> T) {
        val expectedList = nextStateReducers.fold(listOf(initialState)) { currentStates, reducer ->
            currentStates + currentStates.last().reducer()
        }

        values.zip(expectedList) { v, e -> v to e }.forEachIndexed { index, (actual, expected) ->
            Assert.assertEquals(expected, actual)
        }
    }
}