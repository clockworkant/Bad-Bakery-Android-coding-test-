package com.clockworkant.betabakers.DI

import com.clockworkant.betabakers.data.CakeRepo
import com.clockworkant.betabakers.data.CakesRepoFromJson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repoModule = module {
    single { CakesRepoFromJson(androidContext().assets.open("cakes.json")) as CakeRepo }
}