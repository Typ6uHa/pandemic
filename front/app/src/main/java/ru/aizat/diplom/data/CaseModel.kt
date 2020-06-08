package ru.aizat.diplom.data

data class CaseModel(
    val value: List<Value>
) {
    data class Value(
        val latitude: Double,
        val longitude: Double
    )
}