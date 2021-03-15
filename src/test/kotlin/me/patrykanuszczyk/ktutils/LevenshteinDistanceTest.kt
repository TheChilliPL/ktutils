package me.patrykanuszczyk.ktutils

import me.patrykanuszczyk.ktutils.strings.levenshteinDistance
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LevenshteinDistanceTest {
    @Test
    fun test() {
        val a = levenshteinDistance(
            "kitten",
            "sitting"
        )

        assertEquals(3, a)

        val b = levenshteinDistance(
            "Saturday",
            "Sunday"
        )

        assertEquals(3, b)
    }
}