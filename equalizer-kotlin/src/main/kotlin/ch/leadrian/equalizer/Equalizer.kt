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

/**
 * DSL function for a more Kotlin-esque configuration of an `Equals` instance.
 *
 * Example:
 *
 * ```
 * class Person(val name: String) {
 *
 *     companion object {
 *
 *         private val EQUALS = equals<Person> {
 *             compare { name }
 *         }
 *     }
 * }
 * ```
 * @see KotlinEqualsBuilder
 */
inline fun <reified T : Any> equals(block: KotlinEqualsBuilder<T>.() -> Unit): Equals<T> {
    val delegate = Equalizer.equalsBuilder(T::class.java)
    return KotlinEqualsBuilder(delegate).apply(block).build()
}

/**
 * DSL function for a more Kotlin-esque configuration of an `HashCode` instance.
 *
 * Example:
 *
 * ```
 * class Person(val name: String) {
 *
 *     companion object {
 *
 *         private val HASH_CODE = hashCode<Person> {
 *             hash { name }
 *         }
 *     }
 * }
 * ```
 * @see KotlinHashCodeBuilder
 */
inline fun <T : Any> hashCode(block: KotlinHashCodeBuilder<T>.() -> Unit): HashCode<T> {
    val delegate = Equalizer.hashCodeBuilder<T>()
    return KotlinHashCodeBuilder(delegate).apply(block).build()
}

/**
 * DSL function for a more Kotlin-esque configuration of an `EqualsAndHashCode` instance.
 *
 * Example:
 *
 * ```
 * class Person(val firstName: String, val lastName: String) {
 *
 *     companion object {
 *
 *         private val EQUALS_AND_HASH_CODE = equalsAndHashCode<Person> {
 *             compareAndHash { firstName }
 *             compare { lastName }
 *         }
 *     }
 * }
 * ```
 * @see KotlinEqualsAndHashCodeBuilder
 */
inline fun <reified T : Any> equalsAndHashCode(block: KotlinEqualsAndHashCodeBuilder<T>.() -> Unit): EqualsAndHashCode<T> {
    val delegate = Equalizer.equalsAndHashCodeBuilder(T::class.java)
    return KotlinEqualsAndHashCodeBuilder(delegate).apply(block).build()
}

/**
 * Short form of `Equals.equals(first, second)`.
 *
 * Example:
 *
 * ```
 * class Person(val name: String) {
 *
 *     companion object {
 *
 *         private val EQUALS = equals<Person> {
 *             compare { name }
 *         }
 *     }
 *
 *     override fun equals(other: Any?): Boolean = EQUALS(this, other)
 * }
 * ```
 */
operator fun <T : Any> Equals<T>.invoke(first: T, second: Any?): Boolean = equals(first, second)

/**
 * Short form of `HashCode.hashCode(target)`.
 *
 * Example:
 *
 * ```
 * class Person(val name: String) {
 *
 *     companion object {
 *
 *         private val HASH_CODE = hashCode<Person> {
 *             hash { name }
 *         }
 *     }
 *
 *     override fun hashCode(): Int = HASH_CODE(this)
 * }
 * ```
 */
operator fun <T : Any> HashCode<T>.invoke(target: T): Int = hashCode(target)