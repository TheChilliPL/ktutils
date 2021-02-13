package me.patrykanuszczyk.ktutils.locks

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReadWriteLock

inline fun <R> Lock.use(func: () -> R): R {
    lock()
    try {
        return func()
    } finally {
        unlock()
    }
}

inline fun <R> Lock.useInterruptibly(func: () -> R): R {
    lockInterruptibly()
    try {
        return func()
    } finally {
        unlock()
    }
}

inline fun <R> ReadWriteLock.useRead(func: () -> R): R {
    readLock().lock()
    try {
        return func()
    } finally {
        readLock().unlock()
    }
}

inline fun <R> ReadWriteLock.useWrite(func: () -> R): R {
    writeLock().lock()
    try {
        return func()
    } finally {
        writeLock().unlock()
    }
}