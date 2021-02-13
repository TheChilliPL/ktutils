package me.patrykanuszczyk.ktutils.strings

import me.patrykanuszczyk.ktutils.math.xor
import me.patrykanuszczyk.ktutils.random.SecureRandom
import me.patrykanuszczyk.ktutils.random.nextChar
import java.io.Reader

@Suppress("JoinDeclarationAndAssignment")
class PaddedSecureString : SecureString {
    constructor(length: Int) {
        this.chars = CharArray(length)
        this.pad = CharArray(length)
        this.rng = SecureRandom()
    }

    constructor(
        original: CharSequence, start: Int, end: Int
    ): this(original.length) {
        scramble(original, start, end)
    }

    constructor(original: CharSequence) :
        this(original, 0, original.length)

    constructor(
        reader: Reader,
        length: Int
    ): this(length) {
        scramble(reader, length)
    }

    private fun scramble(reader: Reader, length: Int) {
        for(i in 0 until length) {
            pad[i] = rng.nextChar()
            chars[i] = reader.read().toChar() xor pad[i]
        }
    }

    private fun scramble(original: CharSequence, start: Int, end: Int) {
        for (i in start until end) {
            pad[i] = rng.nextChar()
            chars[i] = original[i] xor pad[i]
        }
    }

    private val chars: CharArray
    private val pad: CharArray
    private val rng: SecureRandom

    override fun clear() {
        for(i in indices) {
            chars[i] = rng.nextChar()
            pad[i] = rng.nextChar()
        }
    }

    override val length get() = chars.size

    override fun get(index: Int): Char {
        return chars[index] xor pad[index]
    }

    override fun subSequence(startIndex: Int, endIndex: Int): SecureString {
        return PaddedSecureString(this, startIndex, endIndex)
    }
}