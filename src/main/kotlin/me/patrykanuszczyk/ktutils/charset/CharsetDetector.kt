package me.patrykanuszczyk.ktutils.charset

import java.io.ByteArrayInputStream
import java.lang.IllegalStateException
import java.nio.charset.Charset
import java.util.stream.Collectors.toList
import kotlin.NoSuchElementException

abstract class CharsetDetector<Options : CharsetDetectionOptions> {
    fun detect(stream: ByteArrayInputStream, applyOptions: Options.() -> Unit): Charset {
        val options = getDefaultOptions().apply(applyOptions)

        return detect(stream, options)
    }
    abstract fun detect(stream: ByteArrayInputStream, options: Options): Charset

    abstract fun getDefaultOptions(): Options
}

interface CharsetDetectionOptions

class ExpectedLettersCharsetDetector
    : CharsetDetector<ExpectedLettersCharsetDetectionOptions>()
{
    override fun detect(stream: ByteArrayInputStream, options: ExpectedLettersCharsetDetectionOptions): Charset {
        val bytes = stream.readAllBytes()

        val values = options.checkCharsets.associateWith { charset ->
            var str = bytes.toString(charset)
            if(options.checkFirst > 0) str = str.take(options.checkFirst)
            str.toList().map { char ->
                if(char.isLetter())
                    if(char in options.expectLetters)
                        1.5
                    else
                        0.0
                else
                    1.0
            }.average()
        }

        return values.maxByOrNull { it.value }?.key ?: throw IllegalStateException("There are no charset values.")
    }

    override fun getDefaultOptions() = ExpectedLettersCharsetDetectionOptions()
}

class ExpectedLettersCharsetDetectionOptions : CharsetDetectionOptions {
    var expectLetters = defaultExpectLetters.toMutableSet()

    fun byLanguage(languageCode: String) {
        val split = languageCode.split('-', limit = 2)
        val language = split[0].toLowerCase()
        //val country = split.getOrNull(1)?.toLowerCase()

        expectLetters = when(language) {
            "en" -> defaultExpectLetters
            "de" -> defaultExpectLetters + "ÄäÖöÜüẞß".toSet()
            "es" -> defaultExpectLetters + "ÁáÉéÍíÓóÚúÑñ".toSet()
            "pl" -> defaultExpectLetters + "ĄąĆćĘęŁłŃńÓóŚśŹźŻż".toSet()
            "ru" -> '\u0410'..'\u042F' // Cyrillic
            else -> throw NoSuchElementException("Couldn't find the specified language")
        }.toMutableSet()
    }

    var checkCharsets = defaultCheckCharsets.toMutableSet()

    var checkFirst = 0

    companion object {
        val defaultExpectLetters = ('A'..'Z').toSet() + ('a'..'z').toSet()
        val defaultCheckCharsets = setOf(Charsets.UTF_16, Charsets.UTF_8, Charsets.ISO_8859_1)
    }
}