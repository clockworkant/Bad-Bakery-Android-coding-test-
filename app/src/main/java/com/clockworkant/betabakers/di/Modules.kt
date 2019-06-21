package com.clockworkant.betabakers.di

import com.clockworkant.betabakers.data.*
import org.koin.dsl.module

val repoModule = module {
    //TODO comment out the datasource you don't want to use. Allows for offine development.
//    single { LocalDataSource(androidContext().assets.open("cakes.json")) as DataSource }
    single { RemoteDataSource() as DataSource }
    single { CakeRepoImpl(get()) as CakeRepo }
}