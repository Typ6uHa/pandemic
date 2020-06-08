package ru.aizat.diplom.data

import io.reactivex.Single
import retrofit2.http.GET

interface Service{

    @GET("last")
    fun getStat() : Single<StatsModel>
}