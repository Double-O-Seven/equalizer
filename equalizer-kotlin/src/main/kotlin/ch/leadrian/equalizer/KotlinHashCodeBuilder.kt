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
 * A Kotlin-specific [HashCodeBuilder] that includes some inline functions which provide a simplified Kotlin DSL
 * for Equalizer.
 */
class KotlinHashCodeBuilder<T : Any>
@PublishedApi
internal constructor(delegate: HashCodeBuilder<T>) : HashCodeBuilder<T> by delegate {

    /**
     * Inline variant of [HashCodeBuilder.hash] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see HashCodeBuilder.hash
     */
    inline fun hash(crossinline valueExtractor: T.() -> Any?) {
        hash(Function<T, Any?> { valueExtractor(it) })
    }

    /**
     * Inline variant of [HashCodeBuilder.hashIdentity] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see HashCodeBuilder.hashIdentity
     */
    inline fun hashIdentity(crossinline valueExtractor: T.() -> Any?) {
        hashIdentity(Function<T, Any?> { valueExtractor(it) })
    }

    /**
     * Inline variant of [HashCodeBuilder.hashDeep] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see HashCodeBuilder.hashDeep
     */
    inline fun hashDeep(crossinline valueExtractor: T.() -> Any?) {
        hashDeep(Function<T, Any?> { valueExtractor(it) })
    }

    /**
     * Inline variant of [HashCodeBuilder.hashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see HashCodeBuilder.hashPrimitive
     */
    inline fun hashByte(crossinline valueExtractor: T.() -> Byte) {
        hashPrimitive(ToByteFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [HashCodeBuilder.hashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see HashCodeBuilder.hashPrimitive
     */
    inline fun hashShort(crossinline valueExtractor: T.() -> Short) {
        hashPrimitive(ToShortFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [HashCodeBuilder.hashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see HashCodeBuilder.hashPrimitive
     */
    inline fun hashChar(crossinline valueExtractor: T.() -> Char) {
        hashPrimitive(ToCharFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [HashCodeBuilder.hashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see HashCodeBuilder.hashPrimitive
     */
    inline fun hashInt(crossinline valueExtractor: T.() -> Int) {
        hashPrimitive(ToIntFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [HashCodeBuilder.hashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see HashCodeBuilder.hashPrimitive
     */
    inline fun hashLong(crossinline valueExtractor: T.() -> Long) {
        hashPrimitive(ToLongFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [HashCodeBuilder.hashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see HashCodeBuilder.hashPrimitive
     */
    inline fun hashFloat(crossinline valueExtractor: T.() -> Float) {
        hashPrimitive(ToFloatFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [HashCodeBuilder.hashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see HashCodeBuilder.hashPrimitive
     */
    inline fun hashDouble(crossinline valueExtractor: T.() -> Double) {
        hashPrimitive(ToDoubleFunction<T> { valueExtractor(it) })
    }

    /**
     * Inline variant of [HashCodeBuilder.hashPrimitive] takes accepts a lambda function with an instance of [T] as the receiver.
     *
     * @param valueExtractor the lambda function used to extract a value within the scope of an instance of [T].
     * @see HashCodeBuilder.hashPrimitive
     */
    inline fun hashBoolean(crossinline valueExtractor: T.() -> Boolean) {
        hashPrimitive(Predicate<T> { valueExtractor(it) })
    }

}