package info.novatec.serverless

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlin.math.ceil

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
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

data class BreakEvenRequest(val price: Double, val fixedCosts: Double, val unitCosts: Double)

data class BreakEvenPoint(val breakEvenPoint: Integer)