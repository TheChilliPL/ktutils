package me.patrykanuszczyk.ktutils.annotations

/**
 * This annotation implies that the annotated element is a part of the
 * experimental API and can change between minor versions or be removed.
 */
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Experimental
