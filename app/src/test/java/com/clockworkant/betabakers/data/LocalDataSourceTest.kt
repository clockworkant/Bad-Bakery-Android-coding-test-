package com.clockworkant.betabakers.data

import org.junit.Test
import java.io.BufferedInputStream
import java.io.FileInputStream

class LocalDataSourceTest {
    val cakesPath = this.javaClass.getResource("/cakes.json").path
    val localDataSource = LocalDataSource(BufferedInputStream(FileInputStream(cakesPath)))

    @Test
    fun `assert number of cakes`() {

        localDataSource.getCakes()
            .toObservable()
            .test()
            .assertValue { it.size == 20 }
    }

    @Test
    fun `assert cake deseralised`() {
        val expectedCake = Cake(
            "Lemon cheesecake",
            "A cheesecake made of lemon",
            "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
        )
        localDataSource.getCakes()
            .toObservable()
            .test()
            .assertValue {
                it.first() == expectedCake
            }
    }
}