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

import java.util.function.Function;

/**
 * <p>
 * Combines {@link Equals} and {@link HashCode}. The combined interfaces guarantees
 * that equality checks and hashing are configured in a way such that the contracts between
 * {@link Object#equals(Object)} and {@link Object#hashCode()} are followed, meaning that if two objects are
 * equivalent, then their hash codes are the same.
 * A specific instance may be configured and instantiated using {@link Equalizer#equalsAndHashCodeBuilder(Class)} (Class)}.
 * </p>
 * Example:
 * <pre>
 *     public class Person {
 *
 *         private static final EqualsAndHashCode&lt;Person&gt; EQUALS_AND_HASH_CODE = Equalizer.equalsAndHashCodeBuilder(Person.class)
 *                 .comparePrimitive(Person::getAge)
 *                 .compareAndHash(Person::getName)
 *                 .build();
 *
 *         private final int age;
 *         private final String name;
 *
 *         public Person(int age, String name) {
 *             this.age = age;
 *             this.name = name;
 *         }
 *
 *         public int getAge() {
 *             return age;
 *         }
 *
 *         public String getName() {
 *             return name;
 *         }
 *
 *         &#64;Override
 *         public int hashCode() {
 *             return EQUALS_AND_HASH_CODE.hashCode(this);
 *         }
 *
 *         &#64;Override
 *         public boolean equals(Object object) {
 *             return EQUALS_AND_HASH_CODE.equals(this, object);
 *         }
 *     }
 * </pre>
 *
 * @param <T> Type for which {@link EqualsAndHashCode} has been configured.
 * @see HashCode
 * @see Equals
 * @see EqualsAndHashCodeBuilder
 */
public interface EqualsAndHashCode<T> extends Equals<T>, HashCode<T> {

    /**
     * <p>
     * Convenient factory method to build an {@link EqualsAndHashCode} instance using the given value extractors.
     * The {@code valueExtractors} are applied using {@link EqualsAndHashCodeBuilder#compareAndHash(Function)}.
     * </p>
     * <p>
     * If any primitives or arrays are used to compare or hash values, or if reference equality is checked, it is
     * recommended to build an {@link Equals} using {@link Equalizer#equalsAndHashCodeBuilder(Class)} with appropriate methods
     * like {@link EqualsAndHashCodeBuilder#compareAndHashPrimitive}, {@link EqualsAndHashCodeBuilder#compareAndHashIdentity(Function)}
     * or {@link EqualsAndHashCodeBuilder#compareAndHashDeep(Function)}.
     * </p>
     *
     * @param targetClass     The class for which {@link EqualsAndHashCode} should be used to implement {@link Object#equals(Object)}
     * @param valueExtractors Functions used to extract values from instances of {@code T} to be compared for
     *                        equivalence checks, or to be used to compute hashes. The extracted values may be {@code null}.
     * @param <T>             Type of {@code targetClass}
     * @return an {@link EqualsAndHashCode} instance using the {@code valueExtractors} to compute equivalence or compute hashes
     */
    @SafeVarargs
    static <T> EqualsAndHashCode<T> of(Class<T> targetClass, Function<? super T, ?>... valueExtractors) {
        EqualsAndHashCodeBuilder<T> builder = Equalizer.equalsAndHashCodeBuilder(targetClass);
        for (Function<? super T, ?> valueExtractor : valueExtractors) {
            builder.compareAndHash(valueExtractor);
        }
        return builder.build();
    }

}
