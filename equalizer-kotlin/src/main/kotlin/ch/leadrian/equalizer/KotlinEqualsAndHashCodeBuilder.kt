/*
 * Copyright (C) 2019 Adrian-Philipp Leuenberger
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

class KotlinEqualsAndHashCodeBuilder<T : Any>
@PublishedApi
internal constructor(delegate: EqualsAndHashCodeBuilder<T>) : EqualsAndHashCodeBuilder<T> by delegate {

    inline fun compare(crossinline valueExtractor: T.() -> Any?): EqualsAndHashCodeBuilder<T> {
        return compare(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun compareIdentity(crossinline valueExtractor: T.() -> Any?): EqualsAndHashCodeBuilder<T> {
        return compareIdentity(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun compareDeep(crossinline valueExtractor: T.() -> Any?): EqualsAndHashCodeBuilder<T> {
        return compareDeep(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun compareByte(crossinline valueExtractor: T.() -> Byte): EqualsAndHashCodeBuilder<T> {
        return comparePrimitive(ToByteFunction<T> { valueExtractor(it) })
    }

    inline fun compareShort(crossinline valueExtractor: T.() -> Short): EqualsAndHashCodeBuilder<T> {
        return comparePrimitive(ToShortFunction<T> { valueExtractor(it) })
    }

    inline fun compareChar(crossinline valueExtractor: T.() -> Char): EqualsAndHashCodeBuilder<T> {
        return comparePrimitive(ToCharFunction<T> { valueExtractor(it) })
    }

    inline fun compareInt(crossinline valueExtractor: T.() -> Int): EqualsAndHashCodeBuilder<T> {
        return comparePrimitive(ToIntFunction<T> { valueExtractor(it) })
    }

    inline fun compareLong(crossinline valueExtractor: T.() -> Long): EqualsAndHashCodeBuilder<T> {
        return comparePrimitive(ToLongFunction<T> { valueExtractor(it) })
    }

    inline fun compareFloat(crossinline valueExtractor: T.() -> Float): EqualsAndHashCodeBuilder<T> {
        return comparePrimitive(ToFloatFunction<T> { valueExtractor(it) })
    }

    inline fun compareDouble(crossinline valueExtractor: T.() -> Double): EqualsAndHashCodeBuilder<T> {
        return comparePrimitive(ToDoubleFunction<T> { valueExtractor(it) })
    }

    inline fun compareBoolean(crossinline valueExtractor: T.() -> Boolean): EqualsAndHashCodeBuilder<T> {
        return comparePrimitive(Predicate<T> { valueExtractor(it) })
    }

    inline fun compareAndHash(crossinline valueExtractor: T.() -> Any?): EqualsAndHashCodeBuilder<T> {
        return compareAndHash(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun compareAndHashIdentity(crossinline valueExtractor: T.() -> Any?): EqualsAndHashCodeBuilder<T> {
        return compareAndHashIdentity(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun compareAndHashDeep(crossinline valueExtractor: T.() -> Any?): EqualsAndHashCodeBuilder<T> {
        return compareAndHashDeep(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun compareAndHashByte(crossinline valueExtractor: T.() -> Byte): EqualsAndHashCodeBuilder<T> {
        return compareAndHashPrimitive(ToByteFunction<T> { valueExtractor(it) })
    }

    inline fun compareAndHashShort(crossinline valueExtractor: T.() -> Short): EqualsAndHashCodeBuilder<T> {
        return compareAndHashPrimitive(ToShortFunction<T> { valueExtractor(it) })
    }

    inline fun compareAndHashChar(crossinline valueExtractor: T.() -> Char): EqualsAndHashCodeBuilder<T> {
        return compareAndHashPrimitive(ToCharFunction<T> { valueExtractor(it) })
    }

    inline fun compareAndHashInt(crossinline valueExtractor: T.() -> Int): EqualsAndHashCodeBuilder<T> {
        return compareAndHashPrimitive(ToIntFunction<T> { valueExtractor(it) })
    }

    inline fun compareAndHashLong(crossinline valueExtractor: T.() -> Long): EqualsAndHashCodeBuilder<T> {
        return compareAndHashPrimitive(ToLongFunction<T> { valueExtractor(it) })
    }

    inline fun compareAndHashFloat(crossinline valueExtractor: T.() -> Float): EqualsAndHashCodeBuilder<T> {
        return compareAndHashPrimitive(ToFloatFunction<T> { valueExtractor(it) })
    }

    inline fun compareAndHashDouble(crossinline valueExtractor: T.() -> Double): EqualsAndHashCodeBuilder<T> {
        return compareAndHashPrimitive(ToDoubleFunction<T> { valueExtractor(it) })
    }

    inline fun compareAndHashBoolean(crossinline valueExtractor: T.() -> Boolean): EqualsAndHashCodeBuilder<T> {
        return compareAndHashPrimitive(Predicate<T> { valueExtractor(it) })
    }

}