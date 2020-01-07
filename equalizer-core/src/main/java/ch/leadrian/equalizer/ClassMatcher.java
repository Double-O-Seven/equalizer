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

/**
 * <p>
 * An interface that is used to determine whether two instances of type {@code T} can actually be compared.
 * It can be used to prevent equivalence between two instances of different subclasses of type {@code T}.
 * </p>
 * <p>
 * If the classes do not match, none of the comparison steps configured for an {@link Equals} or {@link EqualsAndHashCode}
 * instance are applied.
 * </p>
 *
 * @param <T> type for which equivalence checks are implemented.
 * @see Equals
 * @see EqualsAndHashCode
 */
@FunctionalInterface
public interface ClassMatcher<T> {

    /**
     * <p>
     * Determines whether the classes of two values can be compared and the values can therefore be potentially
     * equivalent.
     * </p>
     * <p>
     * The implementation MUST guarantee that {@code otherObject} is an instance of type {@code T} if the return value
     * is {@code true} and the values therefore have matching classes.
     * </p>
     * <p>
     * The input values {@code object} and {@code otherObject} are guaranteed to be non-null.
     * </p>
     *
     * @param object      the target value which is known to be an instance of type {@code T}
     * @param otherObject the value which {@code object} is compared against, given their classes match
     * @return {@code true} of {@code object} and {@code otherObject} can be compared, else {@code false}
     */
    boolean classesMatch(T object, Object otherObject);

}
