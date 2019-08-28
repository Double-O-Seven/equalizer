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

import java.util.function.Function;

/**
 * <p>
 * Equivalence check for {@link java.lang.Object#equals(Object)}.
 * A specific instance may be configured and instantiated using {@link Equalizer#equalsBuilder(Class)}.
 * </p>
 * <p>
 * {@link Equals} and {@link HashCode} may be combined using {@link EqualsAndHashCode}.
 * </p>
 * Example:
 * <pre>
 *     public class Person {
 *
 *         private static final Equals&lt;Person&gt; EQUALS = Equalizer.equalsBuilder(Person.class)
 *                 .comparePrimitive(Person::getAge)
 *                 .compare(Person::getName)
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
 *         public boolean equals(Object object) {
 *             return EQUALS.equals(this, object);
 *         }
 *     }
 * </pre>
 *
 * @param <T> Type for which {@link Equals} has been configured.
 * @see HashCode
 * @see EqualsAndHashCode
 * @see EqualsBuilder
 */
public interface Equals<T> {

    /**
     * <p>
     * Convenient factory method to build an {@link Equals} instance using the given value extractors.
     * The {@code valueExtractors} are applied using {@link EqualsBuilder#compare(Function)}.
     * </p>
     * <p>
     * If any primitives or arrays are used to determine equivalence or if reference equality is checked, it is
     * recommended to build an {@link Equals} using {@link Equalizer#equalsBuilder(Class)} with appropriate methods
     * like {@link EqualsBuilder#comparePrimitive}, {@link EqualsBuilder#compareIdentity(Function)} or {@link EqualsBuilder#compareDeep(Function)}.
     * </p>
     *
     * @param targetClass     The class for which {@link Equals} should be used to implement {@link Object#equals(Object)}
     * @param valueExtractors Functions used to extract values from instances of {@code T} to be compared for
     *                        equivalence checks. The extracted values may be {@code null}.
     * @param <T>             Type of {@code targetClass}
     * @return an {@link Equals} instance using the {@code valueExtractors} to compute equivalence
     */
    @SafeVarargs
    static <T> Equals<T> of(Class<T> targetClass, Function<? super T, ?>... valueExtractors) {
        EqualsBuilder<T> builder = Equalizer.equalsBuilder(targetClass);
        for (Function<? super T, ?> valueExtractor : valueExtractors) {
            builder.compare(valueExtractor);
        }
        return builder.build();
    }

    /**
     * @param object      Instance of the class for which {@link java.lang.Object#equals(Object)} has been implemented
     *                    using {@link Equals}. The value can be {@code null}.
     * @param otherObject The nullable value with which {@code object} is compared.
     * @return {@code true} if and only if {@code object} is equivalent to {@code otherObject} according to the
     * specification of {@link java.lang.Object#equals(Object)}, else {@code false}.
     * @see java.lang.Object#equals(Object)
     * @see Equalizer#equalsBuilder(Class)
     */
    boolean equals(T object, Object otherObject);

}
