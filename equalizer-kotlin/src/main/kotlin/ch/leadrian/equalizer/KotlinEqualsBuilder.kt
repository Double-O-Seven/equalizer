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

class KotlinEqualsBuilder<T : Any>
@PublishedApi
internal constructor(delegate: EqualsBuilder<T>) : EqualsBuilder<T> by delegate {

    inline fun <reified U> compare(crossinline valueExtractor: T.() -> U) {
        compare(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun compareIdentity(crossinline valueExtractor: T.() -> Any?) {
        compareIdentity(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun compareDeep(crossinline valueExtractor: T.() -> Any?) {
        compareDeep(Function<T, Any?> { valueExtractor(it) })
    }

    inline fun compareByte(crossinline valueExtractor: T.() -> Byte) {
        comparePrimitive(ToByteFunction<T> { valueExtractor(it) })
    }

    inline fun compareShort(crossinline valueExtractor: T.() -> Short) {
        comparePrimitive(ToShortFunction<T> { valueExtractor(it) })
    }

    inline fun compareChar(crossinline valueExtractor: T.() -> Char) {
        comparePrimitive(ToCharFunction<T> { valueExtractor(it) })
    }

    inline fun compareInt(crossinline valueExtractor: T.() -> Int) {
        comparePrimitive(ToIntFunction<T> { valueExtractor(it) })
    }

    inline fun compareLong(crossinline valueExtractor: T.() -> Long) {
        comparePrimitive(ToLongFunction<T> { valueExtractor(it) })
    }

    inline fun compareFloat(crossinline valueExtractor: T.() -> Float) {
        comparePrimitive(ToFloatFunction<T> { valueExtractor(it) })
    }

    inline fun compareDouble(crossinline valueExtractor: T.() -> Double) {
        comparePrimitive(ToDoubleFunction<T> { valueExtractor(it) })
    }

    inline fun compareBoolean(crossinline valueExtractor: T.() -> Boolean) {
        comparePrimitive(Predicate<T> { valueExtractor(it) })
    }

}