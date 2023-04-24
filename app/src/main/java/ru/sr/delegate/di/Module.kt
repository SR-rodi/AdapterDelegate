package ru.sr.delegate.di

import ru.sr.delegate.data.Repository
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.delegate.presentation.PostViewModel

fun postModule() = module {

    singleOf(::Repository)

    viewModelOf(::PostViewModel)
}