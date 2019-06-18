package com.clockworkant.betabakers.data

import io.reactivex.Single

interface CakeRepo {
    fun getAll(): Single<List<Cake>>
    fun getUniques(): Single<List<Cake>>
}

class CakeRepoImpl(private val dataSource: DataSource) : CakeRepo {
    override fun getAll(): Single<List<Cake>> = dataSource.getCakes()

    override fun getUniques(): Single<List<Cake>> = getAll().map {
        it.toHashSet().toList()
    }
}