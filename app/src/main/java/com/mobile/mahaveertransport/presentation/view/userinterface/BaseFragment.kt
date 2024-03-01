package com.mobile.mahaveertransport.presentation.view.userinterface

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    fun showHideProgressBar(progressBar: ProgressBar?, isLoading: Boolean = false) {
        progressBar?.visibility =
            if (isLoading) View.VISIBLE else View.GONE
    }
}