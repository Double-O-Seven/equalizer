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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.function.Predicate
import java.util.function.ToDoubleFunction
import java.util.function.ToIntFunction
import java.util.function.ToLongFunction

internal class EqualizerTest {

    @Nested
    inner class EqualsTests {

        @Test
        fun `given equal string value it should return true`() {
            val equals = equals<TestObject> {
                compare { stringValue }
            }
            val object1 = TestObject(stringValue = "foo")
            val object2 = TestObject(stringValue = "foo")

            val result = equals(object1, object2)

            assertThat(result)
                    .isTrue()
        }

        @Test
        fun `given different string value it should return false`() {
            val equals = equals<TestObject> {
                compare { stringValue }
            }
            val object1 = TestObject(stringValue = "foo")
            val object2 = TestObject(stringValue = "bar")

            val result = equals(object1, object2)

            assertThat(result)
                    .isFalse()
        }

        @Test
        fun `given same object value it should return true`() {
            val equals = equals<TestObject> {
                compareIdentity { objectValue }
            }
            val value = TestObject()
            val object1 = TestObject(objectValue = value)
            val object2 = TestObject(objectValue = value)

            val result = equals(object1, object2)

            assertThat(result)
                    .isTrue()
        }

        @Test
        fun `given different object value it should return false`() {
            val equals = equals<TestObject> {
                compareIdentity { objectValue }
            }
            val object1 = TestObject(objectValue = TestObject())
            val object2 = TestObject(objectValue = TestObject())

            val result = equals(object1, object2)

            assertThat(result)
                    .isFalse()
        }

        @Test
        fun `given equal array value it should return true`() {
            val equals = equals<TestObject> {
                compareDeep { arrayValue }
            }
            val array1 = TestObject(arrayValue = arrayOf("foo"))
            val array2 = TestObject(arrayValue = arrayOf("foo"))

            val result = equals(array1, array2)

            assertThat(result)
                    .isTrue()
        }

        @Test
        fun `given different array value it should return false`() {
            val equals = equals<TestObject> {
                compareDeep { arrayValue }
            }
            val array1 = TestObject(arrayValue = arrayOf("foo"))
            val array2 = TestObject(arrayValue = arrayOf("bar"))

            val result = equals(array1, array2)

            assertThat(result)
                    .isFalse()
        }

        @Test
        fun `given equal byte value it should return true`() {
            val equals = equals<TestObject> {
                compareByte { byteValue }
            }
            val object1 = TestObject(byteValue = 5)
            val object2 = TestObject(byteValue = 5)

            val result = equals(object1, object2)

            assertThat(result)
                    .isTrue()
        }

        @Test
        fun `given different byte value it should return false`() {
            val equals = equals<TestObject> {
                compareByte { byteValue }
            }
            val object1 = TestObject(byteValue = 5)
            val object2 = TestObject(byteValue = 69)

            val result = equals(object1, object2)

            assertThat(result)
                    .isFalse()
        }

        @Test
        fun `given equal short value it should return true`() {
            val equals = equals<TestObject> {
                compareShort { shortValue }
            }
            val object1 = TestObject(shortValue = 5)
            val object2 = TestObject(shortValue = 5)

            val result = equals(object1, object2)

            assertThat(result)
                    .isTrue()
        }

        @Test
        fun `given different short value it should return false`() {
            val equals = equals<TestObject> {
                compareShort { shortValue }
            }
            val object1 = TestObject(shortValue = 5)
            val object2 = TestObject(shortValue = 69)

            val result = equals(object1, object2)

            assertThat(result)
                    .isFalse()
        }

        @Test
        fun `given equal char value it should return true`() {
            val equals = equals<TestObject> {
                compareChar { charValue }
            }
            val object1 = TestObject(charValue = 'a')
            val object2 = TestObject(charValue = 'a')

            val result = equals(object1, object2)

            assertThat(result)
                    .isTrue()
        }

        @Test
        fun `given different char value it should return false`() {
            val equals = equals<TestObject> {
                compareChar { charValue }
            }
            val object1 = TestObject(charValue = 'a')
            val object2 = TestObject(charValue = 'b')

            val result = equals(object1, object2)

            assertThat(result)
                    .isFalse()
        }

        @Test
        fun `given equal int value it should return true`() {
            val equals = equals<TestObject> {
                compareInt { intValue }
            }
            val object1 = TestObject(intValue = 5)
            val object2 = TestObject(intValue = 5)

            val result = equals(object1, object2)

            assertThat(result)
                    .isTrue()
        }

        @Test
        fun `given different int value it should return false`() {
            val equals = equals<TestObject> {
                compareInt { intValue }
            }
            val object1 = TestObject(intValue = 5)
            val object2 = TestObject(intValue = 69)

            val result = equals(object1, object2)

            assertThat(result)
                    .isFalse()
        }

        @Test
        fun `given equal long value it should return true`() {
            val equals = equals<TestObject> {
                compareLong { longValue }
            }
            val object1 = TestObject(longValue = 5)
            val object2 = TestObject(longValue = 5)

            val result = equals(object1, object2)

            assertThat(result)
                    .isTrue()
        }

        @Test
        fun `given different long value it should return false`() {
            val equals = equals<TestObject> {
                compareLong { longValue }
            }
            val object1 = TestObject(longValue = 5)
            val object2 = TestObject(longValue = 69)

            val result = equals(object1, object2)

            assertThat(result)
                    .isFalse()
        }

        @Test
        fun `given equal float value it should return true`() {
            val equals = equals<TestObject> {
                compareFloat { floatValue }
            }
            val object1 = TestObject(floatValue = 5f)
            val object2 = TestObject(floatValue = 5f)

            val result = equals(object1, object2)

            assertThat(result)
                    .isTrue()
        }

        @Test
        fun `given different float value it should return false`() {
            val equals = equals<TestObject> {
                compareFloat { floatValue }
            }
            val object1 = TestObject(floatValue = 5f)
            val object2 = TestObject(floatValue = 69f)

            val result = equals(object1, object2)

            assertThat(result)
                    .isFalse()
        }

        @Test
        fun `given equal double value it should return true`() {
            val equals = equals<TestObject> {
                compareDouble { doubleValue }
            }
            val object1 = TestObject(doubleValue = 5.0)
            val object2 = TestObject(doubleValue = 5.0)

            val result = equals(object1, object2)

            assertThat(result)
                    .isTrue()
        }

        @Test
        fun `given different double value it should return false`() {
            val equals = equals<TestObject> {
                compareDouble { doubleValue }
            }
            val object1 = TestObject(doubleValue = 5.0)
            val object2 = TestObject(doubleValue = 69.0)

            val result = equals(object1, object2)

            assertThat(result)
                    .isFalse()
        }

        @Test
        fun `given equal boolean value it should return true`() {
            val equals = equals<TestObject> {
                compareBoolean { booleanValue }
            }
            val object1 = TestObject(booleanValue = true)
            val object2 = TestObject(booleanValue = true)

            val result = equals(object1, object2)

            assertThat(result)
                    .isTrue()
        }

        @Test
        fun `given different boolean value it should return false`() {
            val equals = equals<TestObject> {
                compareBoolean { booleanValue }
            }
            val object1 = TestObject(booleanValue = true)
            val object2 = TestObject(booleanValue = false)

            val result = equals(object1, object2)

            assertThat(result)
                    .isFalse()
        }
    }

    @Nested
    inner class HashCodeTests {

        @Test
        fun `should hash string value as with plain Java`() {
            val value = TestObject(stringValue = "foo")
            val expectedResult = Equalizer.hashCodeBuilder<TestObject>()
                    .hash { it.stringValue }
                    .build()
                    .hashCode(value)
            val hashCode = hashCode<TestObject> {
                hash { stringValue }
            }

            val result = hashCode(value)

            assertThat(result)
                    .isEqualTo(expectedResult)
        }

        @Test
        fun `should hash object value as with plain Java`() {
            val value = TestObject(objectValue = Any())
            val expectedResult = Equalizer.hashCodeBuilder<TestObject>()
                    .hash { it.objectValue }
                    .build()
                    .hashCode(value)
            val hashCode = hashCode<TestObject> {
                hashIdentity { objectValue }
            }

            val result = hashCode(value)

            assertThat(result)
                    .isEqualTo(expectedResult)
        }

        @Test
        fun `should hash array value as with plain Java`() {
            val value = TestObject(arrayValue = arrayOf("foo"))
            val expectedResult = Equalizer.hashCodeBuilder<TestObject>()
                    .hashDeep { it.arrayValue }
                    .build()
                    .hashCode(value)
            val hashCode = hashCode<TestObject> {
                hashDeep { arrayValue }
            }

            val result = hashCode(value)

            assertThat(result)
                    .isEqualTo(expectedResult)
        }

        @Test
        fun `should hash byte value as with plain Java`() {
            val value = TestObject(byteValue = 5)
            val expectedResult = Equalizer.hashCodeBuilder<TestObject>()
                    .hashPrimitive(ToByteFunction { it.byteValue })
                    .build()
                    .hashCode(value)
            val hashCode = hashCode<TestObject> {
                hashByte { byteValue }
            }

            val result = hashCode(value)

            assertThat(result)
                    .isEqualTo(expectedResult)
        }

        @Test
        fun `should hash short value as with plain Java`() {
            val value = TestObject(shortValue = 5)
            val expectedResult = Equalizer.hashCodeBuilder<TestObject>()
                    .hashPrimitive(ToShortFunction { it.shortValue })
                    .build()
                    .hashCode(value)
            val hashCode = hashCode<TestObject> {
                hashShort { shortValue }
            }

            val result = hashCode(value)

            assertThat(result)
                    .isEqualTo(expectedResult)
        }

        @Test
        fun `should hash char value as with plain Java`() {
            val value = TestObject(charValue = 'a')
            val expectedResult = Equalizer.hashCodeBuilder<TestObject>()
                    .hashPrimitive(ToCharFunction { it.charValue })
                    .build()
                    .hashCode(value)
            val hashCode = hashCode<TestObject> {
                hashChar { charValue }
            }

            val result = hashCode(value)

            assertThat(result)
                    .isEqualTo(expectedResult)
        }

        @Test
        fun `should hash int value as with plain Java`() {
            val value = TestObject(intValue = 5)
            val expectedResult = Equalizer.hashCodeBuilder<TestObject>()
                    .hashPrimitive(ToIntFunction { it.intValue })
                    .build()
                    .hashCode(value)
            val hashCode = hashCode<TestObject> {
                hashInt { intValue }
            }

            val result = hashCode(value)

            assertThat(result)
                    .isEqualTo(expectedResult)
        }

        @Test
        fun `should hash long value as with plain Java`() {
            val value = TestObject(longValue = 5)
            val expectedResult = Equalizer.hashCodeBuilder<TestObject>()
                    .hashPrimitive(ToLongFunction { it.longValue })
                    .build()
                    .hashCode(value)
            val hashCode = hashCode<TestObject> {
                hashLong { longValue }
            }

            val result = hashCode(value)

            assertThat(result)
                    .isEqualTo(expectedResult)
        }

        @Test
        fun `should hash float value as with plain Java`() {
            val value = TestObject(floatValue = 5f)
            val expectedResult = Equalizer.hashCodeBuilder<TestObject>()
                    .hashPrimitive(ToFloatFunction { it.floatValue })
                    .build()
                    .hashCode(value)
            val hashCode = hashCode<TestObject> {
                hashFloat { floatValue }
            }

            val result = hashCode(value)

            assertThat(result)
                    .isEqualTo(expectedResult)
        }

        @Test
        fun `should hash double value as with plain Java`() {
            val value = TestObject(doubleValue = 5.0)
            val expectedResult = Equalizer.hashCodeBuilder<TestObject>()
                    .hashPrimitive(ToDoubleFunction { it.doubleValue })
                    .build()
                    .hashCode(value)
            val hashCode = hashCode<TestObject> {
                hashDouble { doubleValue }
            }

            val result = hashCode(value)

            assertThat(result)
                    .isEqualTo(expectedResult)
        }

        @Test
        fun `should hash boolean value as with plain Java`() {
            val value = TestObject(booleanValue = true)
            val expectedResult = Equalizer.hashCodeBuilder<TestObject>()
                    .hashPrimitive(Predicate { it.booleanValue })
                    .build()
                    .hashCode(value)
            val hashCode = hashCode<TestObject> {
                hashBoolean { booleanValue }
            }

            val result = hashCode(value)

            assertThat(result)
                    .isEqualTo(expectedResult)
        }
    }

    @Nested
    inner class EqualsAndHashCodeTests {

        @Nested
        inner class CompareTests {

            @Test
            fun `given equal string value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compare { stringValue }
                }
                val object1 = TestObject(stringValue = "foo")
                val object2 = TestObject(stringValue = "foo")

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different string value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compare { stringValue }
                }
                val object1 = TestObject(stringValue = "foo")
                val object2 = TestObject(stringValue = "bar")

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given same object value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareIdentity { objectValue }
                }
                val value = TestObject()
                val object1 = TestObject(objectValue = value)
                val object2 = TestObject(objectValue = value)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different object value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareIdentity { objectValue }
                }
                val object1 = TestObject(objectValue = TestObject())
                val object2 = TestObject(objectValue = TestObject())

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal array value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareDeep { arrayValue }
                }
                val array1 = TestObject(arrayValue = arrayOf("foo"))
                val array2 = TestObject(arrayValue = arrayOf("foo"))

                val result = equals(array1, array2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different array value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareDeep { arrayValue }
                }
                val array1 = TestObject(arrayValue = arrayOf("foo"))
                val array2 = TestObject(arrayValue = arrayOf("bar"))

                val result = equals(array1, array2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal byte value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareByte { byteValue }
                }
                val object1 = TestObject(byteValue = 5)
                val object2 = TestObject(byteValue = 5)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different byte value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareByte { byteValue }
                }
                val object1 = TestObject(byteValue = 5)
                val object2 = TestObject(byteValue = 69)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal short value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareShort { shortValue }
                }
                val object1 = TestObject(shortValue = 5)
                val object2 = TestObject(shortValue = 5)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different short value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareShort { shortValue }
                }
                val object1 = TestObject(shortValue = 5)
                val object2 = TestObject(shortValue = 69)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal char value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareChar { charValue }
                }
                val object1 = TestObject(charValue = 'a')
                val object2 = TestObject(charValue = 'a')

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different char value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareChar { charValue }
                }
                val object1 = TestObject(charValue = 'a')
                val object2 = TestObject(charValue = 'b')

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal int value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareInt { intValue }
                }
                val object1 = TestObject(intValue = 5)
                val object2 = TestObject(intValue = 5)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different int value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareInt { intValue }
                }
                val object1 = TestObject(intValue = 5)
                val object2 = TestObject(intValue = 69)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal long value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareLong { longValue }
                }
                val object1 = TestObject(longValue = 5)
                val object2 = TestObject(longValue = 5)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different long value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareLong { longValue }
                }
                val object1 = TestObject(longValue = 5)
                val object2 = TestObject(longValue = 69)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal float value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareFloat { floatValue }
                }
                val object1 = TestObject(floatValue = 5f)
                val object2 = TestObject(floatValue = 5f)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different float value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareFloat { floatValue }
                }
                val object1 = TestObject(floatValue = 5f)
                val object2 = TestObject(floatValue = 69f)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal double value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareDouble { doubleValue }
                }
                val object1 = TestObject(doubleValue = 5.0)
                val object2 = TestObject(doubleValue = 5.0)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different double value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareDouble { doubleValue }
                }
                val object1 = TestObject(doubleValue = 5.0)
                val object2 = TestObject(doubleValue = 69.0)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal boolean value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareBoolean { booleanValue }
                }
                val object1 = TestObject(booleanValue = true)
                val object2 = TestObject(booleanValue = true)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different boolean value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareBoolean { booleanValue }
                }
                val object1 = TestObject(booleanValue = true)
                val object2 = TestObject(booleanValue = false)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }
        }

        @Nested
        inner class CompareAndHashTests {

            @Test
            fun `given equal string value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHash { stringValue }
                }
                val object1 = TestObject(stringValue = "foo")
                val object2 = TestObject(stringValue = "foo")

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different string value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHash { stringValue }
                }
                val object1 = TestObject(stringValue = "foo")
                val object2 = TestObject(stringValue = "bar")

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given same object value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashIdentity { objectValue }
                }
                val value = TestObject()
                val object1 = TestObject(objectValue = value)
                val object2 = TestObject(objectValue = value)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different object value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashIdentity { objectValue }
                }
                val object1 = TestObject(objectValue = TestObject())
                val object2 = TestObject(objectValue = TestObject())

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal array value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashDeep { arrayValue }
                }
                val array1 = TestObject(arrayValue = arrayOf("foo"))
                val array2 = TestObject(arrayValue = arrayOf("foo"))

                val result = equals(array1, array2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different array value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashDeep { arrayValue }
                }
                val array1 = TestObject(arrayValue = arrayOf("foo"))
                val array2 = TestObject(arrayValue = arrayOf("bar"))

                val result = equals(array1, array2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal byte value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashByte { byteValue }
                }
                val object1 = TestObject(byteValue = 5)
                val object2 = TestObject(byteValue = 5)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different byte value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashByte { byteValue }
                }
                val object1 = TestObject(byteValue = 5)
                val object2 = TestObject(byteValue = 69)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal short value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashShort { shortValue }
                }
                val object1 = TestObject(shortValue = 5)
                val object2 = TestObject(shortValue = 5)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different short value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashShort { shortValue }
                }
                val object1 = TestObject(shortValue = 5)
                val object2 = TestObject(shortValue = 69)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal char value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashChar { charValue }
                }
                val object1 = TestObject(charValue = 'a')
                val object2 = TestObject(charValue = 'a')

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different char value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashChar { charValue }
                }
                val object1 = TestObject(charValue = 'a')
                val object2 = TestObject(charValue = 'b')

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal int value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashInt { intValue }
                }
                val object1 = TestObject(intValue = 5)
                val object2 = TestObject(intValue = 5)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different int value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashInt { intValue }
                }
                val object1 = TestObject(intValue = 5)
                val object2 = TestObject(intValue = 69)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal long value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashLong { longValue }
                }
                val object1 = TestObject(longValue = 5)
                val object2 = TestObject(longValue = 5)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different long value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashLong { longValue }
                }
                val object1 = TestObject(longValue = 5)
                val object2 = TestObject(longValue = 69)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal float value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashFloat { floatValue }
                }
                val object1 = TestObject(floatValue = 5f)
                val object2 = TestObject(floatValue = 5f)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different float value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashFloat { floatValue }
                }
                val object1 = TestObject(floatValue = 5f)
                val object2 = TestObject(floatValue = 69f)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal double value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashDouble { doubleValue }
                }
                val object1 = TestObject(doubleValue = 5.0)
                val object2 = TestObject(doubleValue = 5.0)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different double value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashDouble { doubleValue }
                }
                val object1 = TestObject(doubleValue = 5.0)
                val object2 = TestObject(doubleValue = 69.0)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }

            @Test
            fun `given equal boolean value it should return true`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashBoolean { booleanValue }
                }
                val object1 = TestObject(booleanValue = true)
                val object2 = TestObject(booleanValue = true)

                val result = equals(object1, object2)

                assertThat(result)
                        .isTrue()
            }

            @Test
            fun `given different boolean value it should return false`() {
                val equals = equalsAndHashCode<TestObject> {
                    compareAndHashBoolean { booleanValue }
                }
                val object1 = TestObject(booleanValue = true)
                val object2 = TestObject(booleanValue = false)

                val result = equals(object1, object2)

                assertThat(result)
                        .isFalse()
            }


            @Test
            fun `should hash string value as with plain Java`() {
                val value = TestObject(stringValue = "foo")
                val expectedResult = Equalizer.equalsAndHashCodeBuilder<TestObject>(TestObject::class.java)
                        .compareAndHash { it.stringValue }
                        .build()
                        .hashCode(value)
                val equalsAndHashCode = equalsAndHashCode<TestObject> {
                    compareAndHash { stringValue }
                }

                val result = equalsAndHashCode(value)

                assertThat(result)
                        .isEqualTo(expectedResult)
            }

            @Test
            fun `should hash object value as with plain Java`() {
                val value = TestObject(objectValue = Any())
                val expectedResult = Equalizer.equalsAndHashCodeBuilder<TestObject>(TestObject::class.java)
                        .compareAndHash { it.objectValue }
                        .build()
                        .hashCode(value)
                val equalsAndHashCode = equalsAndHashCode<TestObject> {
                    compareAndHashIdentity { objectValue }
                }

                val result = equalsAndHashCode(value)

                assertThat(result)
                        .isEqualTo(expectedResult)
            }

            @Test
            fun `should hash array value as with plain Java`() {
                val value = TestObject(arrayValue = arrayOf("foo"))
                val expectedResult = Equalizer.equalsAndHashCodeBuilder<TestObject>(TestObject::class.java)
                        .compareAndHashDeep { it.arrayValue }
                        .build()
                        .hashCode(value)
                val equalsAndHashCode = equalsAndHashCode<TestObject> {
                    compareAndHashDeep { arrayValue }
                }

                val result = equalsAndHashCode(value)

                assertThat(result)
                        .isEqualTo(expectedResult)
            }

            @Test
            fun `should hash byte value as with plain Java`() {
                val value = TestObject(byteValue = 5)
                val expectedResult = Equalizer.equalsAndHashCodeBuilder<TestObject>(TestObject::class.java)
                        .compareAndHashPrimitive(ToByteFunction { it.byteValue })
                        .build()
                        .hashCode(value)
                val equalsAndHashCode = equalsAndHashCode<TestObject> {
                    compareAndHashByte { byteValue }
                }

                val result = equalsAndHashCode(value)

                assertThat(result)
                        .isEqualTo(expectedResult)
            }

            @Test
            fun `should hash short value as with plain Java`() {
                val value = TestObject(shortValue = 5)
                val expectedResult = Equalizer.equalsAndHashCodeBuilder<TestObject>(TestObject::class.java)
                        .compareAndHashPrimitive(ToShortFunction { it.shortValue })
                        .build()
                        .hashCode(value)
                val equalsAndHashCode = equalsAndHashCode<TestObject> {
                    compareAndHashShort { shortValue }
                }

                val result = equalsAndHashCode(value)

                assertThat(result)
                        .isEqualTo(expectedResult)
            }

            @Test
            fun `should hash char value as with plain Java`() {
                val value = TestObject(charValue = 'a')
                val expectedResult = Equalizer.equalsAndHashCodeBuilder<TestObject>(TestObject::class.java)
                        .compareAndHashPrimitive(ToCharFunction { it.charValue })
                        .build()
                        .hashCode(value)
                val equalsAndHashCode = equalsAndHashCode<TestObject> {
                    compareAndHashChar { charValue }
                }

                val result = equalsAndHashCode(value)

                assertThat(result)
                        .isEqualTo(expectedResult)
            }

            @Test
            fun `should hash int value as with plain Java`() {
                val value = TestObject(intValue = 5)
                val expectedResult = Equalizer.equalsAndHashCodeBuilder<TestObject>(TestObject::class.java)
                        .compareAndHashPrimitive(ToIntFunction { it.intValue })
                        .build()
                        .hashCode(value)
                val equalsAndHashCode = equalsAndHashCode<TestObject> {
                    compareAndHashInt { intValue }
                }

                val result = equalsAndHashCode(value)

                assertThat(result)
                        .isEqualTo(expectedResult)
            }

            @Test
            fun `should hash long value as with plain Java`() {
                val value = TestObject(longValue = 5)
                val expectedResult = Equalizer.equalsAndHashCodeBuilder<TestObject>(TestObject::class.java)
                        .compareAndHashPrimitive(ToLongFunction { it.longValue })
                        .build()
                        .hashCode(value)
                val equalsAndHashCode = equalsAndHashCode<TestObject> {
                    compareAndHashLong { longValue }
                }

                val result = equalsAndHashCode(value)

                assertThat(result)
                        .isEqualTo(expectedResult)
            }

            @Test
            fun `should hash float value as with plain Java`() {
                val value = TestObject(floatValue = 5f)
                val expectedResult = Equalizer.equalsAndHashCodeBuilder<TestObject>(TestObject::class.java)
                        .compareAndHashPrimitive(ToFloatFunction { it.floatValue })
                        .build()
                        .hashCode(value)
                val equalsAndHashCode = equalsAndHashCode<TestObject> {
                    compareAndHashFloat { floatValue }
                }

                val result = equalsAndHashCode(value)

                assertThat(result)
                        .isEqualTo(expectedResult)
            }

            @Test
            fun `should hash double value as with plain Java`() {
                val value = TestObject(doubleValue = 5.0)
                val expectedResult = Equalizer.equalsAndHashCodeBuilder<TestObject>(TestObject::class.java)
                        .compareAndHashPrimitive(ToDoubleFunction { it.doubleValue })
                        .build()
                        .hashCode(value)
                val equalsAndHashCode = equalsAndHashCode<TestObject> {
                    compareAndHashDouble { doubleValue }
                }

                val result = equalsAndHashCode(value)

                assertThat(result)
                        .isEqualTo(expectedResult)
            }

            @Test
            fun `should hash boolean value as with plain Java`() {
                val value = TestObject(booleanValue = true)
                val expectedResult = Equalizer.equalsAndHashCodeBuilder<TestObject>(TestObject::class.java)
                        .compareAndHashPrimitive(Predicate { it.booleanValue })
                        .build()
                        .hashCode(value)
                val equalsAndHashCode = equalsAndHashCode<TestObject> {
                    compareAndHashBoolean { booleanValue }
                }

                val result = equalsAndHashCode(value)

                assertThat(result)
                        .isEqualTo(expectedResult)
            }
        }
    }

}
