package info.novatec

import io.kotless.dsl.ktor.Kotless
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

class Server : Kotless() {
    override fun prepare(app: Application) {
        app.routing {
            get("/hello") {
                call.respondText { "Hello World!" }
            }
        }
    }
}