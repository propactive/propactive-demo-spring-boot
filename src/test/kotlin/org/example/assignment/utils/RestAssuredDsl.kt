@file:Suppress("TestFunctionName")

package org.example.assignment.utils

import io.restassured.RestAssured
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse
import io.restassured.specification.RequestSender
import io.restassured.specification.RequestSpecification


fun Given(block: RequestSpecification.() -> Unit): RequestSpecification =
    RestAssured.given().apply(block)

infix fun RequestSpecification.When(block: RequestSender.() -> Response): Response =
    this.`when`().run(block)

infix fun Response.Then(function: ValidatableResponse.() -> Unit): ValidatableResponse? =
    this.then().apply(function)