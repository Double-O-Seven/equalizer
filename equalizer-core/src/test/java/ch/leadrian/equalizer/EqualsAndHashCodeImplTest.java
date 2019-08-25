package ch.leadrian.equalizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

        @Nested
        class CompareAndHashTests {

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
                        .compareAndHashPrimitive(TestObject::getIntValue)
                        .compareAndHashPrimitive(TestObject::getLongValue)
                        .compareAndHashPrimitive(TestObject::getDoubleValue)
                        .compareAndHashPrimitive(TestObject::getBooleanValue)
                        .build();
            }

            @Test
            void givenValuesAreTheSameItShouldReturnTrue() {
                TestObject testObject = testObject();

                boolean result = equalsAndHashCode.equals(testObject, testObject);

                assertThat(result)
                        .isTrue();
            }

            @Test
            void givenValuesAreTheSameAndNoComparisonsItShouldReturnTrue() {
                TestObject testObject = testObject();
                Equals<TestObject> equals = new EqualsImpl.Builder<>(TestObject.class).build();

                boolean result = equals.equals(testObject, testObject);

                assertThat(result)
                        .isTrue();
            }

            @Test
            void givenValuesAreNotTheSameAndNoComparisonsItShouldReturnFalse() {
                TestObject testObject1 = testObject();
                TestObject testObject2 = testObject();
                Equals<TestObject> equals = new EqualsImpl.Builder<>(TestObject.class).build();

                boolean result = equals.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenBothValuesAreNullItShouldReturnTrue() {
                boolean result = equalsAndHashCode.equals(null, null);

                assertThat(result)
                        .isTrue();
            }

            @Test
            void givenOnlyFirstValueIsNullItShouldReturnFalse() {
                TestObject testObject = testObject();

                boolean result = equalsAndHashCode.equals(null, testObject);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenOnlySecondValueIsNullItShouldReturnFalse() {
                TestObject testObject = testObject();

                boolean result = equalsAndHashCode.equals(testObject, null);

                assertThat(result)
                        .isFalse();
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

            @Test
            void givenIdentityComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withObjectValue(new Object());
                TestObject testObject2 = testObject().withObjectValue(new Object());

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenShallowComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withStringValue("foo");
                TestObject testObject2 = testObject().withStringValue("bar");

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenIntComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withIntValue(1234);
                TestObject testObject2 = testObject().withIntValue(5678);

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenLongComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withLongValue(1234L);
                TestObject testObject2 = testObject().withLongValue(5678L);

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenDoubleComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withDoubleValue(1234.0);
                TestObject testObject2 = testObject().withDoubleValue(5678.0);

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @ParameterizedTest
            @CsvSource({
                    "true, false",
                    "false, true"
            })
            void givenBooleanComparisonFailsItShouldReturnFalse(boolean booleanValue1, boolean booleanValue2) {
                TestObject testObject1 = testObject().withBooleanValue(booleanValue1);
                TestObject testObject2 = testObject().withBooleanValue(booleanValue2);

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenDeepComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withArrayValue("foo", "bar");
                TestObject testObject2 = testObject().withArrayValue("bla");

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

            @Test
            void givenSuperComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withBaseObjectValue("foo");
                TestObject testObject2 = testObject().withBaseObjectValue("bar");

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

        }

        @Nested
        class CompareTests {

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
                        .comparePrimitive(TestObject::getIntValue)
                        .comparePrimitive(TestObject::getLongValue)
                        .comparePrimitive(TestObject::getDoubleValue)
                        .comparePrimitive(TestObject::getBooleanValue)
                        .build();
            }

            @Test
            void givenValuesAreTheSameItShouldReturnTrue() {
                TestObject testObject = testObject();

                boolean result = equalsAndHashCode.equals(testObject, testObject);

                assertThat(result)
                        .isTrue();
            }

            @Test
            void givenValuesAreTheSameAndNoComparisonsItShouldReturnTrue() {
                TestObject testObject = testObject();
                Equals<TestObject> equals = new EqualsImpl.Builder<>(TestObject.class).build();

                boolean result = equals.equals(testObject, testObject);

                assertThat(result)
                        .isTrue();
            }

            @Test
            void givenValuesAreNotTheSameAndNoComparisonsItShouldReturnFalse() {
                TestObject testObject1 = testObject();
                TestObject testObject2 = testObject();
                Equals<TestObject> equals = new EqualsImpl.Builder<>(TestObject.class).build();

                boolean result = equals.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenBothValuesAreNullItShouldReturnTrue() {
                boolean result = equalsAndHashCode.equals(null, null);

                assertThat(result)
                        .isTrue();
            }

            @Test
            void givenOnlyFirstValueIsNullItShouldReturnFalse() {
                TestObject testObject = testObject();

                boolean result = equalsAndHashCode.equals(null, testObject);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenOnlySecondValueIsNullItShouldReturnFalse() {
                TestObject testObject = testObject();

                boolean result = equalsAndHashCode.equals(testObject, null);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenAllComparisonsSucceedingItShouldReturnTrue() {
                TestObject testObject1 = testObject();
                TestObject testObject2 = testObject();

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isTrue();
            }

            @Test
            void givenIdentityComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withObjectValue(new Object());
                TestObject testObject2 = testObject().withObjectValue(new Object());

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenShallowComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withStringValue("foo");
                TestObject testObject2 = testObject().withStringValue("bar");

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenIntComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withIntValue(1234);
                TestObject testObject2 = testObject().withIntValue(5678);

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenLongComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withLongValue(1234L);
                TestObject testObject2 = testObject().withLongValue(5678L);

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenDoubleComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withDoubleValue(1234.0);
                TestObject testObject2 = testObject().withDoubleValue(5678.0);

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @ParameterizedTest
            @CsvSource({
                    "true, false",
                    "false, true"
            })
            void givenBooleanComparisonFailsItShouldReturnFalse(boolean booleanValue1, boolean booleanValue2) {
                TestObject testObject1 = testObject().withBooleanValue(booleanValue1);
                TestObject testObject2 = testObject().withBooleanValue(booleanValue2);

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
            }

            @Test
            void givenDeepComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withArrayValue("foo", "bar");
                TestObject testObject2 = testObject().withArrayValue("bla");

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

            @Test
            void givenSuperComparisonFailsItShouldReturnFalse() {
                TestObject testObject1 = testObject().withBaseObjectValue("foo");
                TestObject testObject2 = testObject().withBaseObjectValue("bar");

                boolean result = equalsAndHashCode.equals(testObject1, testObject2);

                assertThat(result)
                        .isFalse();
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
                .intValue(1337)
                .longValue(1234L)
                .doubleValue(0.815)
                .booleanValue(true)
                .build();
    }

}