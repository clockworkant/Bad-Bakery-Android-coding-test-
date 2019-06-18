package com.clockworkant.betabakers.ui

import com.clockworkant.betabakers.data.Cake
import com.clockworkant.betabakers.data.CakeRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class CakeListPresenter(
    private val cakeListView: CakeListView,
    private val cakeRepo: CakeRepo
) {
    private val compositeDisposable = CompositeDisposable()

    fun onAppLoaded() {
        compositeDisposable.add(
            cakeRepo.getUniques()
                .subscribe({ list ->
                    cakeListView.showCakes(
                        list.sortedBy { cake -> cake.title }
                    )
                }, {
                    cakeListView.showError(it)
                })
        )
    }

    fun onTerminated() {
        compositeDisposable.clear()
    }
}

interface CakeListView {
    fun showCakes(cakes: List<Cake>)
    fun showError(error: Throwable)
}