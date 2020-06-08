package ru.aizat.diplom.data

data class StatsModel(
    val stats: List<Stat>
) {
    data class Stat(
        val died: Int,
        val healed: Int,
        val region: String,
        val sick: Int
    )
}