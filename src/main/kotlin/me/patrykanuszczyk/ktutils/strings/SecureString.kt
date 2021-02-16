package me.patrykanuszczyk.ktutils.strings

import me.patrykanuszczyk.ktutils.annotations.Experimental
import java.io.Reader
import kotlin.reflect.KClass

//TODO Make SecureString not extend CharSequence.
// Reason: toString() is used in many CharSequence functions, but shouldn't
// represent the actual data, because it can be implicitly converted by
// accident (e.g. when logged).
@Experimental
abstract class SecureString : CharSequence {
    open fun toCharArray() = CharArray(length) { get(it) }

    override fun toString(): String {
        return toCharArray().concatToString()
    }

    /**
     * Copies the (decoded) bytes into a byte array,
     * exactly the same way a [CharArray.copyInto] would do.
     *
     * Remember to clear the array after use, so it doesn't stay
     * in the memory.
     */
    fun copyInto(
        destination: CharArray,
        destinationOffset: Int = 0,
        startIndex: Int = 0,
        endIndex: Int = length
    ): CharArray {
        var j = destinationOffset
        for(i in startIndex until endIndex) {
            destination[j] = get(i)
            j++
        }
        this.toHexBytes()
        return destination
    }

    protected abstract fun clear()

    protected fun finalize() {
        clear()
    }

    companion object {
        fun fromCharSequence(original: CharSequence): SecureString =
            PaddedSecureString(original)

        fun fromReader(reader: Reader, length: Int): SecureString =
            PaddedSecureString(reader, length)

        fun fromResource(clazz: KClass<*>, name: String): SecureString =
            fromResource(clazz.java, name)

        fun fromResource(clazz: Class<*>, name: String): SecureString {
            //TODO Ensure everything gets closed properly!

            val url = clazz.getResource(name)!!
            val connection = url.openConnection()
            val length = connection.contentLength

            if(length < 0)
                throw UnsupportedOperationException(
                    "Unknown length resources not yet supported."
                )

            return fromReader(connection.inputStream.reader(), length)
        }
    }
}