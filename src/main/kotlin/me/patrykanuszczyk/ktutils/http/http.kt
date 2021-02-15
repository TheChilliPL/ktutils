package me.patrykanuszczyk.ktutils.http

import me.patrykanuszczyk.ktutils.annotations.Experimental
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URLDecoder
import java.net.URLEncoder

val HttpURLConnection.isError: Boolean
    get() {
        return responseCode >= 300
    }

val HttpURLConnection.inputOrErrorStream: InputStream
    get() {
        return if (isError) errorStream else inputStream
    }

fun urlEncode(str: String): String {
    return URLEncoder.encode(str, Charsets.UTF_8)
}

fun urlDecode(str: String): String {
    return URLDecoder.decode(str, Charsets.UTF_8)
}

/**
 * Encodes HTTP URL parameters.
 *
 * **Experimental:** Doesn't support array parameters.
 *
 * Doesn't contain a question mark before the parameters.
 */
@Experimental
fun encodeHttpURLParameters(params: Map<String, String>): String {
    return params.map {
        val key = urlEncode(it.key)
        val value = urlEncode(it.value)
        "$key=$value"
    }.joinToString("&")
}