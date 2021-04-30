package me.patrykanuszczyk.ktutils.reflection

import me.patrykanuszczyk.ktutils.reflection.ConstructorProperty.Companion.constructor
import java.lang.reflect.Constructor
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ConstructorProperty(clazz: Class<*>, params: Array<out Class<*>>): ReadOnlyProperty<Any?, Constructor<*>> {
    private val constructor by lazy {
        clazz.getDeclaredConstructor(*params)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Constructor<*> {
        return constructor
    }

    companion object {
        @Suppress("unused")
        inline fun <reified T> Class<T>.constructor(vararg params: Class<*>) =
            ConstructorProperty(T::class.java, params)
    }
}