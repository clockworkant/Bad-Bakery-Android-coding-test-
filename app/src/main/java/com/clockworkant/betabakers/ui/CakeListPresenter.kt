package com.clockworkant.betabakers.ui

import com.clockworkant.betabakers.data.Cake
import com.clockworkant.betabakers.data.CakeRepo

class CakeListPresenter(
    private val cakeListView: CakeListView,
    private val cakeRepo: CakeRepo
) {
    fun onAppLoaded() {
        val uniques = cakeRepo.getUniques()
        //TODO ensure cakes are ordered
        cakeListView.showCakes(uniques)
    }
}

interface CakeListView {
    fun showCakes(cakes: Set<Cake>)
}