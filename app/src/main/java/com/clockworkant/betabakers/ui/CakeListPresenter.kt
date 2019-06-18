package com.clockworkant.betabakers.ui

import com.clockworkant.betabakers.data.Cake
import com.clockworkant.betabakers.data.CakeRepo

class CakeListPresenter(
    private val cakeListView: CakeListView,
    private val cakeRepo: CakeRepo
) {
    fun onAppLoaded() {
        val uniques = cakeRepo.getUniques()
        val cakes = uniques.sortedBy { it.title }
        cakeListView.showCakes(cakes)
    }
}

interface CakeListView {
    fun showCakes(cakes: List<Cake>)
}