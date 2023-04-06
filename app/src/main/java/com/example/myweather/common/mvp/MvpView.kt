package com.example.myweather.common.mvp

import androidx.annotation.StringRes

interface MvpView {
    fun showErrorMessage(t: Throwable? = null)
//    fun showErrorMessage(@StringRes messageRes: Int)
}