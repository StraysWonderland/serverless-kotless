package info.novatec

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
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


