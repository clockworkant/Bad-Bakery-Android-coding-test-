package com.clockworkant.betabakers.data

import com.google.gson.Gson
import java.io.InputStream
import java.io.InputStreamReader

interface DataSource {
    fun getCakes(): List<Cake>
}

class LocalDataSource(cakeInputStream: InputStream) : DataSource {
    private val cakes: List<Cake> = Gson().fromJson(InputStreamReader(cakeInputStream), Array<Cake>::class.java).toList()

    override fun getCakes() = cakes

}