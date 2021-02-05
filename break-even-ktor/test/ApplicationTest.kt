package info.novatec.serverless

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class ApplicationTest {
    @Test
    fun testRequests() = withTestApplication(Application::module) {
        val call = handleRequest(HttpMethod.Post, "/") {
            addHeader(HttpHeaders.Accept, ContentType.Application.Json.toString())
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
    }
}
