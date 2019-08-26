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
 * <p>
 * Hash code computation for {@link Object#hashCode()}.
 * A specific instance may be configured and instantiated using {@link Equalizer#hashCode()}.
 * </p>
 * <p>
 * {@link Equals} and {@link HashCode} may be combined using {@link EqualsAndHashCode}.
 * </p>
 * Example:
 * <pre>
 *     public class Person {
 *
 *         private static final HashCode&lt;Person&gt; HASH_CODE = Equalizer.&lt;Person&gt;hashCodeBuilder()
 *                 .hashPrimitive(Person::getAge)
 *                 .hash(Person::getName)
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
 *             return HASH_CODE.hashCode(this);
 *         }
 *     }
 * </pre>
 *
 * @param <T> Type for which {@link HashCode} has been configured.
 * @see Equals
 * @see EqualsAndHashCode
 * @see HashCodeBuilder
 */
public interface HashCode<T> {

    /**
     * Computes the hash code for a nullable value of type {@code T}.
     *
     * @param object The instance of type {@code T} for which a hash code should be computed. The value may be {@code null}.
     * @return Hash code for {@code object}.
     */
    int hashCode(T object);

}
