package me.patrykanuszczyk.ktutils.reflection

import me.patrykanuszczyk.ktutils.reflection.MethodProperty.Companion.method
import java.lang.reflect.Method
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MethodProperty(clazz: Class<*>, name: String, params: Array<out Class<*>>): ReadOnlyProperty<Any, Method> {
    private val method by lazy {
        clazz.findMethod(name, *params)
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): Method {
        return method
    }

    companion object {
        @Suppress("unused")
        inline fun <reified T> Class<T>.method(name: String, vararg params: Class<*>) =
            MethodProperty(T::class.java, name, params)
    }
}

class Test {
    val clazz by Test::class
    val method by clazz.method("get", Int::class.javaPrimitiveType!!)
}