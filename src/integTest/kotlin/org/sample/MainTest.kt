package org.sample

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/*
This is not a actual integration test but just to show how can you
configure multiple folder for different test.
 */
class MainTest : StringSpec({

    "sum of 2 and 3 is 5" {
        sum(2, 3) shouldBe 5
    }
})