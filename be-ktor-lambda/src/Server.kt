package info.novatec.serverless

import io.kotless.dsl.ktor.Kotless
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import kotlin.math.ceil


class Server : Kotless() {
    override fun prepare(app: Application) {
        app.install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
                serializeNulls()
            }
        }
        app.routing {
            get("/") {
                call.respondText { "Hello World!" }
            }
            post("/") {

                val breakEvenRequest = call.receive<BreakEvenRequest>()
                val breakEvenPoint =
                    ceil(breakEvenRequest.fixedCosts / (breakEvenRequest.price - breakEvenRequest.unitCosts)).toInt()
                call.respond(breakEvenPoint)
            }
        }
    }
}

@Serializable
private data class BreakEvenRequest(val price: Double, val fixedCosts: Double, val unitCosts: Double)
