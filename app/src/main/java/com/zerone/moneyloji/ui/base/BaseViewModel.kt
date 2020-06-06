package com.zerone.moneyloji.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import org.koin.core.KoinApplication
import org.koin.core.KoinComponent
import java.lang.Appendable

abstract class BaseViewModel() : ViewModel() , KoinComponent {

}