package com.learning.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {


    routing {
        trace{application.log.trace(it.buildText())}
        get("/") {
            call.respondText("Hello World!")
        }
        route ("/weather"){

            get ("/usa"){
                call.respondText { "The weather in US : Snow" }
            }
            route("europe",HttpMethod.Get){
                header("systemtoken","weathersystem"){
                    param("name"){
                        handle {
                            var name =   call.parameters.get("name")
                            call.respondText { "Dear $name The weather in europe: sunny" }
                        }
                    }
                    handle {
                        call.respondText { "The weather in europe: sunny" }
                    }
                }

            }
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
        install(StatusPages) {
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }

        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
