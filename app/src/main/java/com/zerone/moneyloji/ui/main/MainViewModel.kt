package com.zerone.moneyloji.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.zerone.moneyloji.R
import com.zerone.moneyloji.ui.base.BaseViewModel
import java.util.*

class MainViewModel(private val application: Context) : BaseViewModel() {

    private val resources = application.resources

    val jsonString : MutableLiveData<String> = MutableLiveData()


     fun loadGrades() {
        val inputStream = resources.openRawResource(R.raw.data)
        val scanner = Scanner(inputStream)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine())
        }
        jsonString.postValue(stringBuilder.toString())
    }



}