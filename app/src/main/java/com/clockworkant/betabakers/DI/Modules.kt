package com.clockworkant.betabakers.DI

import com.clockworkant.betabakers.data.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val repoModule = module {
    single { LocalDataSource(androidContext().assets.open("cakes.json")) as DataSource }
//    single { RemoteDataSource() as DataSource }
    single { CakeRepoImpl(get()) as CakeRepo }
}