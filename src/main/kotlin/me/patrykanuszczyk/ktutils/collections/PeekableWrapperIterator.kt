package me.patrykanuszczyk.ktutils.collections

class PeekableWrapperIterator<out T> internal constructor(private val innerIterator: Iterator<T>) :
    PeekableIterator<T> {
    private var has: Boolean = innerIterator.hasNext()
    private var next: T? = if(has) innerIterator.next() else null

    override fun next(): T {
        val peek = peek()
        has = innerIterator.hasNext()
        if (has) next = innerIterator.next()
        return peek
    }

    @Suppress("UNCHECKED_CAST")
    override fun peek() = if (has) (next as T) else throw
    NoSuchElementException()

    override fun hasNext() = has
}