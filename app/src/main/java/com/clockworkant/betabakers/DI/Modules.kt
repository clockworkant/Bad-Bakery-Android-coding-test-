package com.clockworkant.betabakers.DI

import com.clockworkant.betabakers.data.CakeRepo
import com.clockworkant.betabakers.data.CakeRepoImpl
import com.clockworkant.betabakers.data.DataSource
import com.clockworkant.betabakers.data.LocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repoModule = module {
    single { LocalDataSource(androidContext().assets.open("cakes.json")) as DataSource }
    single { CakeRepoImpl(get()) as CakeRepo }
}