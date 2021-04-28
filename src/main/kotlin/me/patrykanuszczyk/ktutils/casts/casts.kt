package me.patrykanuszczyk.ktutils.casts

import kotlin.reflect.KClass

@Suppress("UNUSED_PARAMETER")
inline infix fun <T1 : Any, reified T2 : T1> T1.maybeAs(clazz: KClass<T2>): T2? {
    return if(this is T2) this else null
}