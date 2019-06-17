package com.clockworkant.betabakers.data

import com.beust.klaxon.Klaxon
import java.io.InputStream

class CakesRepoFromJson(cakeInputStream: InputStream) : CakeRepo {
    private val cakes: List<Cake> = Klaxon().parseArray(cakeInputStream)!!

    override fun getAll(): List<Cake>  = cakes

    override fun getUniques(): Set<Cake> = cakes.toHashSet()
}