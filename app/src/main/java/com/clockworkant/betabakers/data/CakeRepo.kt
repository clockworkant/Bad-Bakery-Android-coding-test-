package com.clockworkant.betabakers.data

interface CakeRepo {
    fun getAll(): List<Cake>
    fun getUniques(): Set<Cake>

}