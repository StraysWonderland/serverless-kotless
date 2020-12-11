package com.example.kotless

data class BreakEvenRequest(
    val price: Double,
    val fixedCosts: Double,
    val unitCosts: Double
)