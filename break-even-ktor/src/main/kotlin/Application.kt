package info.novatec

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlin.math.ceil

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

//@Suppress("unused") // Referenced in application.conf
//@kotlin.jvm.JvmOverloads
//fun Application.module(testing: Boolean = false) {

//    install(ContentNegotiation) {
//        gson {
//            setPrettyPrinting()
//            serializeNulls()
//        }
//    }
//    routing {
//        post("/") {
//            val breakEvenRequest = call.receive<BreakEvenRequest>()
//            val breakEvenPoint =
//                ceil(breakEvenRequest.fixedCosts / (breakEvenRequest.price - breakEvenRequest.unitCosts)).toInt()
//            call.respond(breakEvenPoint)
//        }
//    }
//}


fun Application.main() {
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

