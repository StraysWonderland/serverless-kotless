import com.fasterxml.jackson.annotation.JsonProperty
import io.kotless.dsl.lang.http.Get
import io.kotless.dsl.lang.http.Post
import kotlin.math.ceil

@Post("/")
fun execute(request: BreakEvenRequest): BreakEvenResponse {
    val breakEvenPoint = ceil(request.fixedCosts / (request.price - request.unitCosts)).toInt()
    return BreakEvenResponse(breakEvenPoint = breakEvenPoint)
}

@Get("/withParams")
fun executeWithParams(
    @JsonProperty("price") price: Double,
    @JsonProperty("fixedCosts") fixedCosts: Double,
    @JsonProperty("unitCosts") unitCosts: Double
): Int {
    return ceil(fixedCosts / (price - unitCosts)).toInt()
}
