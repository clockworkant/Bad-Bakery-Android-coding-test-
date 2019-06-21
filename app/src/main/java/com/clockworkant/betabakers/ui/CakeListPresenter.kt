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
        refreshCakes()
    }

    private fun refreshCakes() {
        cakeListView.showLoading(true)
        compositeDisposable.add(
            cakeRepo.getUniques()
                .subscribe({ list ->
                    cakeListView.showLoading(false)
                    cakeListView.showCakes(
                        list.sortedBy { cake -> cake.title }
                    )
                }, {
                    cakeListView.showLoading(false)
                    //TODO future - parse error and show different errors if it's helpful to the user
                    cakeListView.showError(it)
                })
        )
    }

    fun onTerminated() {
        compositeDisposable.clear()
    }

    fun onRefresh() {
        refreshCakes()
    }
}

interface CakeListView {
    fun showCakes(cakes: List<Cake>)
    fun showError(error: Throwable)
    fun showLoading(isLoading: Boolean)
}