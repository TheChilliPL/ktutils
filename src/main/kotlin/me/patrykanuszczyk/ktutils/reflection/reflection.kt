package me.patrykanuszczyk.ktutils.reflection

import java.lang.reflect.Method
import kotlin.reflect.KClass

/**
 * Makes a string representation of a method signature.
 *
 * It works the same as the private function [Class.methodToString].
 */
fun methodCallToString(clazz: Class<*>, name: String, vararg params: Class<*>) =
    clazz.name + "." + name +
        params.joinToString(",", "(", ")") {
            it.name ?: "null"
        }

fun Class<*>.tryGetDeclaredMethod(name: String, vararg params: Class<*>): Method? {
    return try {
        getDeclaredMethod(name, *params)
    } catch(exception: NoSuchMethodException) {
        null
    }
}

fun Class<*>.tryFindMethod(name: String, vararg params: Class<*>): Method? {
    return tryGetDeclaredMethod(name, *params)
        ?: superclass.tryFindMethod(name, *params)
}

fun Class<*>.findMethod(name: String, vararg params: Class<*>)
    = tryFindMethod(name, *params)
    ?: throw NoSuchMethodException(methodCallToString(this, name, *params))