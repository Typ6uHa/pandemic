package ru.aizat.diplom.data

import io.reactivex.Single
import retrofit2.http.GET

interface HerokyService {
    @GET("VirusCase")
    fun getVirusCases(): Single<CaseModel>
}