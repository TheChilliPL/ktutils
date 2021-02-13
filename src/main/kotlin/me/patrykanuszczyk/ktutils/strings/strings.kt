package me.patrykanuszczyk.ktutils.strings

fun String.withPrefix(prefix: String): String {
    return prefix + this
}

fun String.withSuffix(suffix: String): String {
    return this + suffix
}

fun getRandomString(characters: Collection<Char>, length: Int): String {
    val builder = StringBuilder(length)
    for(i in 0 until length)
        builder.append(characters.random())
    return builder.toString()
}