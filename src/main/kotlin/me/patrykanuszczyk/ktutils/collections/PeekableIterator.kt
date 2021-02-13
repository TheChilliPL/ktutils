package me.patrykanuszczyk.ktutils.collections

interface PeekableIterator<out T> : Iterator<T> {
    override fun next(): T
    fun peek(): T

    override operator fun hasNext(): Boolean

    companion object {
        operator fun <T> invoke(iterable: Iterable<T>): PeekableIterator<T> {
            return PeekableWrapperIterator(iterable.iterator())
        }

        operator fun <T> invoke(sequence: Sequence<T>): PeekableIterator<T> {
            return PeekableWrapperIterator(sequence.iterator())
        }

        fun <T> Iterable<T>.peekableIterator() = PeekableIterator(this)
        fun <T> Sequence<T>.peekableIterator() = PeekableIterator(this)
    }
}


