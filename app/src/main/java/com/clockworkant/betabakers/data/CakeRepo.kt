package com.clockworkant.betabakers.data

interface CakeRepo {
    fun getAll(): List<Cake>
    fun getUniques(): Set<Cake>
}

class CakeRepoImpl(private val dataSource: DataSource) : CakeRepo {
    override fun getAll(): List<Cake> = dataSource.getCakes()

    override fun getUniques(): Set<Cake> = getAll().toHashSet()
}