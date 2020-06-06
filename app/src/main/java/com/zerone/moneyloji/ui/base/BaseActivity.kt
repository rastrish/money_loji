package com.zerone.moneyloji.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<VM : BaseViewModel>(clazz: KClass<VM>) : AppCompatActivity() {

    val viewModel : VM by viewModel(clazz)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupView(savedInstanceState)
        setupObservers()

    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected open fun setupObservers() {


    }
    protected abstract fun setupView(savedInstanceState: Bundle?)
}