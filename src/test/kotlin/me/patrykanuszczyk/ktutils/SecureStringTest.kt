package me.patrykanuszczyk.ktutils

import me.patrykanuszczyk.ktutils.strings.SecureString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SecureStringTest {
    @Test
    fun fromString() {
        val password = "super-secret-password"
        val secure = SecureString.fromCharSequence(password)

        assertEquals(password, secure.toString())
    }

    @Test
    fun fromResource() {
        val password = "super-secret-resource"
        val secure = SecureString.fromResource(this::class, "sst_resource")

        assertEquals(password, secure.toString())
    }
}