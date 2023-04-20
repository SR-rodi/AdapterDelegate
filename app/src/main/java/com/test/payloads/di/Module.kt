package com.test.payloads.di

import com.test.payloads.data.Repository
import com.test.payloads.presentation.PostViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun postModule() = module {

    singleOf(::Repository)

    viewModelOf(::PostViewModel)
}