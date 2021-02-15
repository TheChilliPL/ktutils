package me.patrykanuszczyk.ktutils.strings

import java.io.Reader
import kotlin.reflect.KClass

abstract class SecureString : CharSequence {
    open val asCharArray get() = CharArray(length) { get(it) }

    open val asString: String
        get() {
            return asCharArray.concatToString()
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