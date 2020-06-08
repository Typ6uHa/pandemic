package ru.aizat.diplom.data

import io.reactivex.Single

class HerokyRepository {

    private val herokuService: HerokyService = ApiFactory.getHerokuService(HerokyService::class)

    fun getCases(): Single<CaseModel> {
        return herokuService.getVirusCases()
    }
}