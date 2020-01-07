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

class KotlinHashCodeBuilder<T : Any>
@PublishedApi
internal constructor(delegate: HashCodeBuilder<T>) : HashCodeBuilder<T> by delegate {

    inline fun hash(crossinline valueExtractor: T.() -> Any?) {
        hash(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun hashIdentity(crossinline valueExtractor: T.() -> Any?) {
        hashIdentity(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun hashDeep(crossinline valueExtractor: T.() -> Any?) {
        hashDeep(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun hashByte(crossinline valueExtractor: T.() -> Byte) {
        hashPrimitive(ToByteFunction<T> { valueExtractor(it) })
    }

    inline fun hashShort(crossinline valueExtractor: T.() -> Short) {
        hashPrimitive(ToShortFunction<T> { valueExtractor(it) })
    }

    inline fun hashChar(crossinline valueExtractor: T.() -> Char) {
        hashPrimitive(ToCharFunction<T> { valueExtractor(it) })
    }

    inline fun hashInt(crossinline valueExtractor: T.() -> Int) {
        hashPrimitive(ToIntFunction<T> { valueExtractor(it) })
    }

    inline fun hashLong(crossinline valueExtractor: T.() -> Long) {
        hashPrimitive(ToLongFunction<T> { valueExtractor(it) })
    }

    inline fun hashFloat(crossinline valueExtractor: T.() -> Float) {
        hashPrimitive(ToFloatFunction<T> { valueExtractor(it) })
    }

    inline fun hashDouble(crossinline valueExtractor: T.() -> Double) {
        hashPrimitive(ToDoubleFunction<T> { valueExtractor(it) })
    }

    inline fun hashBoolean(crossinline valueExtractor: T.() -> Boolean) {
        hashPrimitive(Predicate<T> { valueExtractor(it) })
    }

}