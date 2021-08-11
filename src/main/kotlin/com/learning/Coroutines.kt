package com.learning

import kotlinx.coroutines.*
import kotlinx.css.i
import kotlin.random.Random


fun main(args:Array<String>)= runBlocking{
    withContext(Dispatchers.IO){
       repeat(200_000){
           launch {
               firstcoroutine(it)
           }
       }

        println("done with the context")

    }
    println("End of the main function")
}
suspend fun firstcoroutine(id:Int){
    delay(Random.nextLong()%2000)
    println("first $id")
}