package me.patrykanuszczyk.ktutils.strings

fun levenshteinDistance(stringA: CharSequence, stringB: CharSequence): Int {
    val lengthA = stringA.length
    val lengthB = stringB.length
    val matrix = IntArray((lengthA+1)*(lengthB+1))

    operator fun IntArray.get(a: Int, b: Int): Int {
        return matrix[a * (lengthB+1) + b]
    }

    operator fun IntArray.set(a: Int, b: Int, value: Int) {
        matrix[a * (lengthB+1) + b] = value
    }

    for(b in 1..lengthB)
        matrix[0, b] = b

    for(a in 1..lengthA) {
        matrix[a, 0] = a

        for(b in 1..lengthB) {
            val cost = if(stringA[a-1] == stringB[b-1]) 0 else 1

            matrix[a, b] = minOf(
                matrix[a-1, b] + 1,
                matrix[a, b-1] + 1,
                matrix[a-1, b-1] + cost
            )
        }
    }

    return matrix[lengthA, lengthB]
}