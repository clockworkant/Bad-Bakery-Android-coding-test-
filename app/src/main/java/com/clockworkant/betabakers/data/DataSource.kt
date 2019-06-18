package com.clockworkant.betabakers.data

import com.beust.klaxon.Klaxon
import java.io.InputStream

interface DataSource{
    fun getCakes(): List<Cake>
}

class LocalDataSource(cakeInputStream: InputStream) : DataSource {
    private val cakes: List<Cake> = Klaxon().parseArray(cakeInputStream)!!

    override fun getCakes() = cakes

}