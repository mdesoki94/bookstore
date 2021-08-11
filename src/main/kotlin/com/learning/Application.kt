package com.learning

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.learning.plugins.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureTemplating()
        configureSecurity()
        configureMonitoring()
        configureHTTP()
        configureSerialization()
    }.start(wait = true)

}


