package ru.aizat.diplom.data

import android.annotation.SuppressLint
import com.google.gson.Gson
import io.reactivex.Single

class Repository {

    private val authService: Service = ApiFactory.getRetrofitService(Service::class)

    @SuppressLint("CheckResult")
    fun getStats(flag: Boolean): Single<StatsModel> {
        return if (flag) {
            authService.getStat().map {
                it.stats.map { it ->
                    StatsModel.Stat(
                        it.died,
                        it.healed,
                        it.region,
                        it.sick
                    )
                }
            }.map { it ->
                StatsModel(it.sortedByDescending { it.sick + it.healed + it.died })
            }
        } else {
            authService.getStat().map {
                it.stats.map { it ->
                    StatsModel.Stat(
                        (it.died * 0.8).toInt(),
                        (it.healed * 0.8).toInt(),
                        it.region,
                        (it.sick * 0.8).toInt()
                    )
                }
            }.map { it ->
                StatsModel(it.sortedByDescending { it.sick + it.healed + it.died })
            }
        }
    }
}