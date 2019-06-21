package com.clockworkant.betabakers.data

import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.InputStream
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit


interface DataSource {
    fun getCakes(): Single<List<Cake>>
}

class LocalDataSource(cakeInputStream: InputStream) : DataSource {
    private val cakes: List<Cake> =
        Gson().fromJson(InputStreamReader(cakeInputStream), Array<Cake>::class.java).toList()

    override fun getCakes() = Single.just(cakes)
        .delay(2, TimeUnit.SECONDS) //The delay is to emulate a web call (for progress spinners etc)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}

class RemoteDataSource : DataSource {
    private val service: CakeService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        service = retrofit.create(CakeService::class.java)
    }

    override fun getCakes(): Single<List<Cake>> {
        return service.getCakes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private interface CakeService {
        @GET("https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/waracle_cake-android-client")
        fun getCakes(): Single<List<Cake>>
    }
}