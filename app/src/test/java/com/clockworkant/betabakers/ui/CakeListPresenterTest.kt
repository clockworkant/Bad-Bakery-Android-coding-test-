package com.clockworkant.betabakers.ui

import com.clockworkant.betabakers.data.Cake
import com.clockworkant.betabakers.data.CakeRepo
import com.clockworkant.betabakers.data.CakeRepoImpl
import com.clockworkant.betabakers.data.LocalDataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import java.io.BufferedInputStream
import java.io.FileInputStream

class CakeListPresenterTest {

    private val cakeListView = mock<CakeListView>()

    private val cakeListPresenter = CakeListPresenter(cakeListView, testCakeRepo())

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `on load when repo has duplicates show only distinct cakes`() {

        cakeListPresenter.onAppLoaded()

        verify(cakeListView).showCakes(listOf(
            Cake(title="Banana cake", desc="Donkey kongs favourite", image="http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg"),
            Cake(title="Birthday cake", desc="a yearly treat", image="https://www.frenchvillagebakery.co.uk/databaseimages/prd_8594342__painted_pink_and_gold_cake_512x640.jpg"),
            Cake(title="Carrot cake", desc="Bugs bunnys favourite", image="https://hips.hearstapps.com/del.h-cdn.co/assets/18/08/1519321610-carrot-cake-vertical.jpg"),
            Cake(title="Lemon cheesecake", desc="A cheesecake made of lemon", image="https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"),
            Cake(title="victoria sponge", desc="sponge with jam", image="https://upload.wikimedia.org/wikipedia/commons/0/05/111rfyh.jpg")
        ))
    }

    private fun testCakeRepo(): CakeRepo {
        val cakesPath = this.javaClass.getResource("/cakes.json").path
        val localDataSource = LocalDataSource(BufferedInputStream(FileInputStream(cakesPath)))
        return CakeRepoImpl(localDataSource)
    }
}
