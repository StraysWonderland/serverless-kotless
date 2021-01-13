package info.novatec
import kotlinx.serialization.Serializable

@Serializable
data class BreakEvenRequest(val fixedCosts: Double, val unitCosts: Double, val price: Double)