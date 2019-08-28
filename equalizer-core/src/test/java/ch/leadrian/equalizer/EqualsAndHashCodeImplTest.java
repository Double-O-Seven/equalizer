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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class EqualsAndHashCodeImplTest {

    private static Object OBJECT = new Object();

    @Test
    void builderShouldBuildEqualsAndHashCodeImpl() {
        EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class).build();

        assertThat(equalsAndHashCode)
                .isInstanceOf(EqualsAndHashCodeImpl.class);
    }

    @Nested
    class EqualsTests {

        @Test
        void givenValuesAreTheSameAndNoComparisonsItShouldReturnTrue() {
            TestObject testObject = testObject();
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class).build();

            boolean result = equalsAndHashCode.equals(testObject, testObject);

            assertThat(result)
                    .isTrue();
        }

        @Test
        void givenValuesAreNotTheSameAndNoComparisonsItShouldReturnFalse() {
            TestObject testObject1 = testObject();
            TestObject testObject2 = testObject();
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class).build();

            boolean result = equalsAndHashCode.equals(testObject1, testObject2);

            assertThat(result)
                    .isFalse();
        }

        @Test
        void givenClassesDoNotMatchItShouldReturnFalse() {
            TestObject testObject1 = testObject();
            TestObject testObject2 = testObject();
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compare(TestObject::getStringValue)
                    .classMatcher((v1, v2) -> false)
                    .build();

            boolean result = equalsAndHashCode.equals(testObject1, testObject2);

            assertThat(result)
                    .isFalse();
        }

        @Test
        void givenDelegatingComparisonFailsItShouldReturnFalse() {
            TestObject testObject1 = testObject();
            TestObject testObject2 = testObject();
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compare(TestObject::getStringValue)
                    .equalIf((value1, value2) -> false)
                    .build();

            boolean result = equalsAndHashCode.equals(testObject1, testObject2);

            assertThat(result)
                    .isFalse();
        }

        @Nested
        class CompareAndHashTests implements EqualsTestBase {

            private EqualsAndHashCode<TestObject> equalsAndHashCode;

            @BeforeEach
            void setUp() {
                EqualsAndHashCode<TestObjectBase> superEqualsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObjectBase.class)
                        .compareAndHash(TestObjectBase::getBaseObjectValue)
                        .build();
                equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                        .withSuper(superEqualsAndHashCode)
                        .compareAndHash(TestObject::getStringValue)
                        .compareAndHashIdentity(TestObject::getObjectValue)
                        .compareAndHashDeep(TestObject::getArrayValue)
                        .compareAndHashPrimitive(TestObject::getByteValue)
                        .compareAndHashPrimitive(TestObject::getShortValue)
                        .compareAndHashPrimitive(TestObject::getCharValue)
                        .compareAndHashPrimitive(TestObject::getIntValue)
                        .compareAndHashPrimitive(TestObject::getLongValue)
                        .compareAndHashPrimitive(TestObject::getFloatValue)
                        .compareAndHashPrimitive(TestObject::getDoubleValue)
                        .compareAndHashPrimitive(TestObject::getBooleanValue)
                        .build();
            }

            @Override
            public ImmutableTestObject getTestObject() {
                return testObject();
            }

            @Override
            public Equals<TestObject> getEquals() {
                return equalsAndHashCode;
            }

            @Test
            void givenAllComparisonsSucceedingItShouldReturnTrueAndHaveSameHashCode() {
                TestObject testObject1 = testObject();
                TestObject testObject2 = testObject();

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);
                int hashCode1 = equalsAndHashCode.hashCode(testObject1);
                int hashCode2 = equalsAndHashCode.hashCode(testObject2);

                assertAll(
                        () -> assertThat(result).isTrue(),
                        () -> assertThat(hashCode1).isEqualTo(hashCode2)
                );
            }

        }

        @Nested
        class CompareTests implements EqualsTestBase {

            private EqualsAndHashCode<TestObject> equalsAndHashCode;

            @BeforeEach
            void setUp() {
                EqualsAndHashCode<TestObjectBase> superEqualsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObjectBase.class)
                        .compareAndHash(TestObjectBase::getBaseObjectValue)
                        .build();
                equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                        .withSuper(superEqualsAndHashCode)
                        .compare(TestObject::getStringValue)
                        .compareIdentity(TestObject::getObjectValue)
                        .compareDeep(TestObject::getArrayValue)
                        .comparePrimitive(TestObject::getByteValue)
                        .comparePrimitive(TestObject::getShortValue)
                        .comparePrimitive(TestObject::getCharValue)
                        .comparePrimitive(TestObject::getIntValue)
                        .comparePrimitive(TestObject::getLongValue)
                        .comparePrimitive(TestObject::getFloatValue)
                        .comparePrimitive(TestObject::getDoubleValue)
                        .comparePrimitive(TestObject::getBooleanValue)
                        .build();
            }

            @Override
            public Equals<TestObject> getEquals() {
                return equalsAndHashCode;
            }

            @Override
            public ImmutableTestObject getTestObject() {
                return testObject();
            }

            @Test
            void givenAllComparisonsSucceedingItShouldReturnTrueAndHaveSameHashCode() {
                TestObject testObject1 = testObject();
                TestObject testObject2 = testObject();

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);
                int hashCode1 = equalsAndHashCode.hashCode(testObject1);
                int hashCode2 = equalsAndHashCode.hashCode(testObject2);

                assertAll(
                        () -> assertThat(result).isTrue(),
                        () -> assertThat(hashCode1).isEqualTo(hashCode2)
                );
            }

        }

    }

    @Nested
    class HashCodeTests {

        @Test
        void givenValueIsNullItShouldReturnZero() {
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class).build();

            int result = equalsAndHashCode.hashCode(null);

            assertThat(result)
                    .isZero();
        }

        @Test
        void givenNoHashStepsItShouldReturnSystemIdentityHashCode() {
            TestObject testObject = testObject();
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class).build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(System.identityHashCode(testObject));
        }

        @Test
        void shouldCombineHashSteps() {
            TestObject testObject = testObject()
                    .withIntValue(1337)
                    .withStringValue("Test");
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHashPrimitive(TestObject::getIntValue)
                    .compareAndHash(TestObject::getStringValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 * (31 + Integer.hashCode(1337)) + "Test".hashCode());
        }

        @Test
        void shouldUseStringHashStep() {
            TestObject testObject = testObject()
                    .withStringValue("Test");
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHash(TestObject::getStringValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 + "Test".hashCode());
        }

        @Test
        void shouldUseIdentityHashStep() {
            Object value = new Object();
            TestObject testObject = testObject()
                    .withObjectValue(value);
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHashIdentity(TestObject::getObjectValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 + System.identityHashCode(value));
        }

        @Test
        void shouldUseDeepHashStep() {
            TestObject testObject = testObject()
                    .withArrayValue("foo", "bar");
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHashDeep(TestObject::getArrayValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 + Arrays.deepHashCode(new Object[]{"foo", "bar"}));
        }

        @Test
        void shouldUseByteHashStep() {
            TestObject testObject = testObject()
                    .withByteValue((byte) 5);
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHashPrimitive(TestObject::getByteValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 + Byte.hashCode((byte) 5));
        }

        @Test
        void shouldUseShortHashStep() {
            TestObject testObject = testObject()
                    .withShortValue((short) 5);
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHashPrimitive(TestObject::getShortValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 + Short.hashCode((short) 5));
        }

        @Test
        void shouldUseCharHashStep() {
            TestObject testObject = testObject()
                    .withCharValue('c');
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHashPrimitive(TestObject::getCharValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 + Character.hashCode('c'));
        }

        @Test
        void shouldUseIntHashStep() {
            TestObject testObject = testObject()
                    .withIntValue(1337);
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHashPrimitive(TestObject::getIntValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 + Integer.hashCode(1337));
        }

        @Test
        void shouldUseLongHashStep() {
            TestObject testObject = testObject()
                    .withLongValue(1337L);
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHashPrimitive(TestObject::getLongValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 + Long.hashCode(1337L));
        }

        @Test
        void shouldUseFloatHashStep() {
            TestObject testObject = testObject()
                    .withFloatValue(1337.0f);
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHashPrimitive(TestObject::getFloatValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 + Float.hashCode(1337.0f));
        }

        @Test
        void shouldUseDoubleHashStep() {
            TestObject testObject = testObject()
                    .withDoubleValue(1337.0);
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHashPrimitive(TestObject::getDoubleValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 + Double.hashCode(1337.0));
        }

        @ParameterizedTest
        @ValueSource(booleans = {true, false})
        void shouldUseBooleanHashStep(boolean booleanValue) {
            TestObject testObject = testObject()
                    .withBooleanValue(booleanValue);
            EqualsAndHashCode<TestObject> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestObject.class)
                    .compareAndHashPrimitive(TestObject::getBooleanValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testObject);

            assertThat(result)
                    .isEqualTo(31 + Boolean.hashCode(booleanValue));
        }
    }

    @SuppressWarnings("StringOperationCanBeSimplified")
    private static ImmutableTestObject testObject() {
        return ImmutableTestObject
                .builder()
                .baseObjectValue("Base")
                .stringValue(new String("Test"))
                .objectValue(OBJECT)
                .arrayValue("foo", "bar")
                .byteValue((byte) 5)
                .shortValue((short) 9876)
                .charValue('c')
                .intValue(1337)
                .longValue(1234L)
                .floatValue(13.37f)
                .doubleValue(0.815)
                .booleanValue(true)
                .build();
    }

}