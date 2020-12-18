package com.example.kotless

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotless.dsl.lang.KotlessContext
import io.kotless.dsl.lang.http.Get
import io.kotless.dsl.lang.http.Post
import kotlin.math.ceil
import kotlin.text.Charsets.UTF_8

@Post("/")
fun execute(): String {
    val objectMapper = createObjectMapper()
    val requestBody = KotlessContext.HTTP.request.body?.let { String(it, UTF_8) }
    val breakEvenRequest = objectMapper.readValue(requestBody, BreakEvenRequest::class.java)
    val breakEvenPoint =
        ceil(breakEvenRequest.fixedCosts / (breakEvenRequest.price - breakEvenRequest.unitCosts)).toInt()
    return objectMapper.writeValueAsString(BreakEvenResponse(breakEvenPoint))
}

fun createObjectMapper() =
    ObjectMapper().findAndRegisterModules().setSerializationInclusion(JsonInclude.Include.NON_NULL)

@Get("/withParams")
fun executeWithParams(
    @JsonProperty("price") price: Double,
    @JsonProperty("fixedCosts") fixedCosts: Double,
    @JsonProperty("unitCosts") unitCosts: Double
): Int {
    return ceil(fixedCosts / (price - unitCosts)).toInt()
}

@Get("/hello")
fun sayHello() = "Say Hello!"

