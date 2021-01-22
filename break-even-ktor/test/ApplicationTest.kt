package info.novatec.serverless

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.features.*
import kotlin.test.*
import io.ktor.server.testing.*

class ApplicationTest {
    @Test
    fun testRequests() = withTestApplication(Application::module) {
        val call = handleRequest(HttpMethod.Post, "/") {
            addHeader(HttpHeaders.Accept,ContentType.Application.Json.toString())
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(
                """{
                        "price":20.0,
                        "fixedCosts":100.0,
                        "unitCosts":10.0 
                        }"""
            )
        }
        with(call) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("10", response.content)
        }
        with(handleRequest(HttpMethod.Get, "/index.html")) {
            assertFalse(requestHandled)
        }
    }
}
