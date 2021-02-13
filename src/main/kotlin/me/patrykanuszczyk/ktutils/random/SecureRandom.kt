package me.patrykanuszczyk.ktutils.random

import kotlin.random.Random
import java.security.SecureRandom as JavaRandom

class SecureRandom : Random {
    private val random: InternalRandom

    constructor() {
        random = InternalRandom()
    }

    constructor(seed: ByteArray) {
        random = InternalRandom(seed)
    }

    override fun nextBits(bitCount: Int): Int {
        return random.nextBits(bitCount)
    }

    /**
     * On JVM, the default Java implementation
     * with the next(int) method made public.
     */
    private class InternalRandom : JavaRandom {
        constructor(): super()
        constructor(seed: ByteArray): super(seed)

        fun nextBits(bits: Int) = super.next(bits)
    }
}