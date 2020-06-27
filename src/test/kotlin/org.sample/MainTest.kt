package org.sample

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MainTest : StringSpec({
    "length should return size of string" {
        "hello".length shouldBe 5
    }
})