package org.sample

import spark.Spark.get

fun main() {
    Main().start()
}

class Main() {
    fun start() {
        get("/hello") { _, _ ->
            """
               {
                "message", "Hello here"
               }
           """.trimIndent()
        }
    }
}

fun sum(a: Int, b: Int): Int {
    return a + b
}