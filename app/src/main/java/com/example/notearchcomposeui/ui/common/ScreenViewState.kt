package com.example.notearchcomposeui.ui.common

/**
 * @author : Mingaleev D
 * @data : 06.11.2023
 */

sealed class ScreenViewState<out T> {

   object Loading : ScreenViewState<Nothing>()
   data class Success<T>(val data:T):ScreenViewState<T>()
   data class Error(val error:String?):ScreenViewState<Nothing>()
}