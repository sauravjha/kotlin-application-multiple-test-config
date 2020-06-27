package org.sample

import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/*
This is not a actual integration test but just to show how can you
configure multiple folder for different test.
 */
class MainTest : StringSpec({
//    beforeSpec {
//        Main().start()
//        Spark.awaitInitialization()
//    }
    val client = OkHttpClient()

    val request: Request = Request.Builder()
            .url("https://www.vogella.com/index.html")
            .build()
    val response = client.newCall(request).execute()
    "makes the request successfully" {
        response.isSuccessful shouldBe true
    }

//    afterSpec {
//        Spark.stop()
//    }
})