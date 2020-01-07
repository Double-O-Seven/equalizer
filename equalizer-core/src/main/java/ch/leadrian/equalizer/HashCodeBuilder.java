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

package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToByteFunction;
import ch.leadrian.equalizer.util.function.ToCharFunction;
import ch.leadrian.equalizer.util.function.ToFloatFunction;
import ch.leadrian.equalizer.util.function.ToShortFunction;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * A builder for {@link HashCode}. It is used to configure which attributes of type {@link T} are used to
 * compute the hash code of an instance of type {@code T}.
 *
 * @param <T> type for which an {@link HashCode} instance should be built
 * @see HashCode
 */
public interface HashCodeBuilder<T> {

    /**
     * @param superHashCode {@link HashCode} used for a superclass of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> withSuper(HashCode<? super T> superHashCode);

    /**
     * Use the hash code of the value provided by {@code valueExtractor} to compute the hash code of an instance of {@code T}.
     * The value passed to the {@code valueExtractor} is guaranteed to be non-null. The return value may be null though.
     *
     * @param valueExtractor function to extract a value from an instance of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> hash(Function<? super T, ?> valueExtractor);

    /**
     * Use the hash code of the primitive value provided by {@code valueExtractor} to compute the hash code of an instance of {@code T}.
     * The value passed to the {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor function to extract a value from an instance of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> hashPrimitive(ToByteFunction<? super T> valueExtractor);

    /**
     * Use the hash code of the primitive value provided by {@code valueExtractor} to compute the hash code of an instance of {@code T}.
     * The value passed to the {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor function to extract a value from an instance of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> hashPrimitive(ToShortFunction<? super T> valueExtractor);

    /**
     * Use the hash code of the primitive value provided by {@code valueExtractor} to compute the hash code of an instance of {@code T}.
     * The value passed to the {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor function to extract a value from an instance of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> hashPrimitive(ToCharFunction<? super T> valueExtractor);

    /**
     * Use the hash code of the primitive value provided by {@code valueExtractor} to compute the hash code of an instance of {@code T}.
     * The value passed to the {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor function to extract a value from an instance of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> hashPrimitive(ToIntFunction<? super T> valueExtractor);

    /**
     * Use the hash code of the primitive value provided by {@code valueExtractor} to compute the hash code of an instance of {@code T}.
     * The value passed to the {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor function to extract a value from an instance of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> hashPrimitive(ToLongFunction<? super T> valueExtractor);

    /**
     * Use the hash code of the primitive value provided by {@code valueExtractor} to compute the hash code of an instance of {@code T}.
     * The value passed to the {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor function to extract a value from an instance of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> hashPrimitive(ToFloatFunction<? super T> valueExtractor);

    /**
     * Use the hash code of the primitive value provided by {@code valueExtractor} to compute the hash code of an instance of {@code T}.
     * The value passed to the {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor function to extract a value from an instance of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> hashPrimitive(ToDoubleFunction<? super T> valueExtractor);

    /**
     * Use the hash code of the primitive value provided by {@code valueExtractor} to compute the hash code of an instance of {@code T}.
     * The value passed to the {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor function to extract a value from an instance of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> hashPrimitive(Predicate<? super T> valueExtractor);

    /**
     * Use the deep hash code of the primitive value provided by {@code valueExtractor} to compute the hash code of an instance of {@code T}.
     * The value passed to the {@code valueExtractor} is guaranteed to be non-null. The return value may be null.
     * If the extracted value is an object array or primitive array, the hash code will be computed using the contents of the array, else
     * the default hash code will be computed.
     *
     * @param valueExtractor function to extract a value from an instance of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> hashDeep(Function<? super T, ?> valueExtractor);

    /**
     * Use the hash code of the value provided by {@code valueExtractor} to compute the hash code of an instance of {@code T}.
     * The value passed to the {@code valueExtractor} is guaranteed to be non-null. The return value may be null though.
     * {@link System#identityHashCode(Object)} is used to determine the the hash code of the value provided by {@code valueExtractor}.
     *
     * @param valueExtractor function to extract a value from an instance of {@code T}
     * @return {@code this}
     */
    HashCodeBuilder<T> hashIdentity(Function<? super T, ?> valueExtractor);

    /**
     * Builds an {@link HashCode} instance.
     *
     * @return A {@link HashCode} that uses the configured {@code valueExtractor}s to compute the hash code of an instance of {@code T}.
     */
    HashCode<T> build();

}
