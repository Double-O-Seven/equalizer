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

package ch.leadrian.equalizer;

/**
 * Class containing factory methods for builders.
 *
 * @see EqualsBuilder
 * @see HashCodeBuilder
 * @see EqualsAndHashCodeBuilder
 */
public final class Equalizer {

    private Equalizer() {
    }

    /**
     * @param targetClass The class for which {@link Equals} should be used to implement {@link Object#equals(Object)}
     * @param <T>         Type of {@code targetClass}
     * @return {@link EqualsBuilder} for class {@code targetClass}
     */
    public static <T> EqualsBuilder<T> equalsBuilder(Class<T> targetClass) {
        return new EqualsImpl.Builder<>(targetClass);
    }

    /**
     * @param <T> Type of the class for which {@link HashCode} should be used to implement {@link Object#hashCode()}
     * @return {@link EqualsBuilder} for type {@code T}
     */
    public static <T> HashCodeBuilder<T> hashCodeBuilder() {
        return new HashCodeImpl.Builder<>();
    }

    /**
     * @param targetClass The class for which {@link EqualsAndHashCode} should be used to
     *                    implement {@link Object#equals(Object)} and {@link Object#hashCode()}
     * @param <T>         Type of {@code targetClass}
     * @return {@link EqualsAndHashCodeBuilder} for class {@code targetClass}
     */
    public static <T> EqualsAndHashCodeBuilder<T> equalsAndHashCodeBuilder(Class<T> targetClass) {
        return new EqualsAndHashCodeImpl.Builder<>(targetClass);
    }
}
