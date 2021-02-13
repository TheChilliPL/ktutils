package me.patrykanuszczyk.ktutils.collections

fun List<Int>.normalize(max: Int): List<Int> {
    if(isEmpty()) return emptyList()
    val initMax = this.maxOrNull()!!
    if(initMax == 0) return List(size) {0}
    return map { it * max / initMax }
}

fun <E> MutableCollection<E>.addAll(vararg elements: E): Boolean {
    return this.addAll(elements)
}

fun <E> MutableCollection<E>.addUnique(element: E) {
    if(!contains(element)) add(element)
}

fun <T> Iterator<T>.nextOrNull(): T? {
    return if(hasNext()) next() else null
}

fun <T> PeekableIterator<T>.peekOrNull(): T? {
    return if(hasNext()) peek() else null
}

fun <L, E> L.swap(i: Int, j: Int): L
    where L : MutableList<E> = also {
    val temp = it[i]
    it[i] = it[j]
    it[j] = temp
}