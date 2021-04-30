package me.patrykanuszczyk.ktutils.reflection

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

operator fun <T : Any> KClass<T>.getValue(thisRef: Any, property: KProperty<*>): Class<T> {
    return java
}
