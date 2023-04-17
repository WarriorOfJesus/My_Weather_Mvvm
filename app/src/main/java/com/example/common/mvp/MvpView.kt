package com.example.common.mvp

import androidx.annotation.StringRes

interface MvpView {
    fun showErrorMessage(t: Throwable)
    fun showErrorMessage(@StringRes messageRes: Int)

}