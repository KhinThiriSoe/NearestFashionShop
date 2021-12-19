package com.khin.fashion.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khin.fashion.core.domain.coroutines.CoroutineDispatcherProvider

/**
 * Base view model to have injected viewModelExecutor and common ViewState and ViewEvent
 */
abstract class BaseViewModel<VS : Any, VE : Any>(
    protected val dispatcherProvider: CoroutineDispatcherProvider,
    initialState: VS,
) : ViewModel() {

    protected val mutableViewState = MutableLiveData(initialState)
    val viewState: LiveData<VS> = mutableViewState
    val currentViewState get() = mutableViewState.value!!

    protected fun postState(value: VS) {
        mutableViewState.postValue(value)
    }

    protected inline fun postViewState(builder: VS.() -> VS) {
        postState(currentViewState.builder())
    }

    protected fun setViewState(value: VS) {
        mutableViewState.value = value
    }

    protected inline fun setViewState(builder: VS.() -> VS) {
        mutableViewState.value = currentViewState.builder()
    }
}