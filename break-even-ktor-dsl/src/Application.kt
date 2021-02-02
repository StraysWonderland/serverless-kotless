package info.novatec

import io.kotless.dsl.ktor.Kotless
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import kotlin.math.ceil

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            serializeNulls()
        }
    }
    routing {
        post("/") {
            val breakEvenRequest = call.receive<BreakEvenRequest>()
            val breakEvenPoint =
                ceil(breakEvenRequest.fixedCosts / (breakEvenRequest.price - breakEvenRequest.unitCosts)).toInt()
            call.respond(breakEvenPoint)
        }
    }
}

@Serializable
data class BreakEvenRequest(val price: Double, val fixedCosts: Double, val unitCosts: Double)

class Server : Kotless() {
    override fun prepare(app: Application) {}
}