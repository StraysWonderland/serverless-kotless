package info.novatec

import kotlinx.serialization.Serializable

@Serializable
data class BreakEvenRequest(val price: Double, val fixedCosts: Double, val unitCosts: Double)
