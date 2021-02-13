package me.patrykanuszczyk.ktutils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ResultTest {
    @Test
    fun successTest() {
        val result: Result<Int, Int> = Result.Success(123)

        assertEquals(123, result.successValue)
        assertNull(result.failValue)

        var isCalled = false
        result.ifSuccess { isCalled = true }
        assertTrue(isCalled)

        isCalled = false
        result.ifFailed { isCalled = true }
        assertFalse(isCalled)

        assertEquals(123, result.fail {
            org.junit.jupiter.api.fail {
                "Success result called fail callback."
            }
        })
    }

    @Test
    fun failTest() {
        val result: Result<Int, Int> = Result.Failure(123)

        assertEquals(123, result.failValue)
        assertNull(result.successValue)

        var isCalled = false
        result.ifSuccess { isCalled = true }
        assertFalse(isCalled)

        isCalled = false
        result.ifFailed { isCalled = true }
        assertTrue(isCalled)

        assertThrows(RuntimeException::class.java) {
            result.fail {
                throw RuntimeException()
            }
        }
    }
}