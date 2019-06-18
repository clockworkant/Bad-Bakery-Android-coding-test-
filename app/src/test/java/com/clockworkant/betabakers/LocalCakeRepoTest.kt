package com.clockworkant.betabakers

import com.clockworkant.betabakers.data.CakeRepo
import com.clockworkant.betabakers.data.CakeRepoImpl
import com.clockworkant.betabakers.data.LocalDataSource
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import java.io.BufferedInputStream
import java.io.FileInputStream

class LocalCakeRepoTest {
    val cakesPath = this.javaClass.getResource("/cakes.json").path

    val cakesRepo = CakeRepoImpl(LocalDataSource(BufferedInputStream(FileInputStream(cakesPath)))) as CakeRepo

    @Test
    fun `assert number of cakes`(){
        val all = cakesRepo.getAll()
        assertThat(all.size, equalTo(20))
    }

    @Test
    fun `assert cake deseralised`(){
        val first = cakesRepo.getAll().first()
        assertThat(first.title, equalTo("Lemon cheesecake"))
        assertThat(first.desc, equalTo("A cheesecake made of lemon"))
        assertThat(first.image, equalTo("https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"))
    }

    @Test
    fun `test distinct filtering`() {
        val uniques = cakesRepo.getUniques()
        assertThat(uniques.size, equalTo(5))
    }
}