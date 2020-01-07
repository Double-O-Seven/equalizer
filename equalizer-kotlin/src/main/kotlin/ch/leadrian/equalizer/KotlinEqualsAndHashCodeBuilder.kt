/*
 * Copyright (C) 2020 Adrian-Philipp Leuenberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.leadrian.equalizer

import ch.leadrian.equalizer.util.function.ToByteFunction
import ch.leadrian.equalizer.util.function.ToCharFunction
import ch.leadrian.equalizer.util.function.ToFloatFunction
import ch.leadrian.equalizer.util.function.ToShortFunction
import java.util.function.Function
import java.util.function.Predicate
import java.util.function.ToDoubleFunction
import java.util.function.ToIntFunction
import java.util.function.ToLongFunction

/**
 * A Kotlin-specific [EqualsAndHashCodeBuilder] that includes some inline functions which provide a simplified Kotlin DSL
 * for Equalizer.
 */
class KotlinEqualsAndHashCodeBuilder<T : Any>
@PublishedApi
internal constructor(delegate: EqualsAndHashCodeBuilder<T>) : EqualsAndHashCodeBuilder<T> by delegate {

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compare] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compare
     */
    inline fun compare(crossinline valueExtractor: T.() -> Any?) {
        compare(Function<T, Any?> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareIdentity] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareIdentity
     */
    inline fun compareIdentity(crossinline valueExtractor: T.() -> Any?) {
        compareIdentity(Function<T, Any?> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareDeep] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareDeep
     */
    inline fun compareDeep(crossinline valueExtractor: T.() -> Any?) {
        compareDeep(Function<T, Any?> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.comparePrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.comparePrimitive
     */
    inline fun compareByte(crossinline valueExtractor: T.() -> Byte) {
        comparePrimitive(ToByteFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.comparePrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.comparePrimitive
     */
    inline fun compareShort(crossinline valueExtractor: T.() -> Short) {
        comparePrimitive(ToShortFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.comparePrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.comparePrimitive
     */
    inline fun compareChar(crossinline valueExtractor: T.() -> Char) {
        comparePrimitive(ToCharFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.comparePrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.comparePrimitive
     */
    inline fun compareInt(crossinline valueExtractor: T.() -> Int) {
        comparePrimitive(ToIntFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.comparePrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.comparePrimitive
     */
    inline fun compareLong(crossinline valueExtractor: T.() -> Long) {
        comparePrimitive(ToLongFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.comparePrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.comparePrimitive
     */
    inline fun compareFloat(crossinline valueExtractor: T.() -> Float) {
        comparePrimitive(ToFloatFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.comparePrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.comparePrimitive
     */
    inline fun compareDouble(crossinline valueExtractor: T.() -> Double) {
        comparePrimitive(ToDoubleFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.comparePrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.comparePrimitive
     */
    inline fun compareBoolean(crossinline valueExtractor: T.() -> Boolean) {
        comparePrimitive(Predicate<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareAndHash] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareAndHash
     */
    inline fun compareAndHash(crossinline valueExtractor: T.() -> Any?) {
        compareAndHash(Function<T, Any?> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareAndHashIdentity] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareAndHashIdentity
     */
    inline fun compareAndHashIdentity(crossinline valueExtractor: T.() -> Any?) {
        compareAndHashIdentity(Function<T, Any?> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareAndHashDeep] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareAndHashDeep
     */
    inline fun compareAndHashDeep(crossinline valueExtractor: T.() -> Any?) {
        compareAndHashDeep(Function<T, Any?> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareAndHashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareAndHashPrimitive
     */
    inline fun compareAndHashByte(crossinline valueExtractor: T.() -> Byte) {
        compareAndHashPrimitive(ToByteFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareAndHashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareAndHashPrimitive
     */
    inline fun compareAndHashShort(crossinline valueExtractor: T.() -> Short) {
        compareAndHashPrimitive(ToShortFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareAndHashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareAndHashPrimitive
     */
    inline fun compareAndHashChar(crossinline valueExtractor: T.() -> Char) {
        compareAndHashPrimitive(ToCharFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareAndHashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareAndHashPrimitive
     */
    inline fun compareAndHashInt(crossinline valueExtractor: T.() -> Int) {
        compareAndHashPrimitive(ToIntFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareAndHashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareAndHashPrimitive
     */
    inline fun compareAndHashLong(crossinline valueExtractor: T.() -> Long) {
        compareAndHashPrimitive(ToLongFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareAndHashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareAndHashPrimitive
     */
    inline fun compareAndHashFloat(crossinline valueExtractor: T.() -> Float) {
        compareAndHashPrimitive(ToFloatFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareAndHashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareAndHashPrimitive
     */
    inline fun compareAndHashDouble(crossinline valueExtractor: T.() -> Double) {
        compareAndHashPrimitive(ToDoubleFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [EqualsAndHashCodeBuilder.compareAndHashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see EqualsAndHashCodeBuilder.compareAndHashPrimitive
     */
    inline fun compareAndHashBoolean(crossinline valueExtractor: T.() -> Boolean) {
        compareAndHashPrimitive(Predicate<T> { valueExtractor(it) })
    }

}