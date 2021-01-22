package io.ktor.samples.testable

import info.novatec.main
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class ApplicationTest {
    @Test
    fun testRequests() = withTestApplication(Application::main) {
        val call = handleRequest(HttpMethod.Post, "/") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            setBody(listOf("price" to "10.0", "fixedCosts" to "100.0", "unitCosts" to "20.0").formUrlEncode())
        }
        with(call) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Hello from Ktor Testable sample application", response.content)
        }
        with(handleRequest(HttpMethod.Get, "/index.html")) {
            assertFalse(requestHandled)
        }
    }
}