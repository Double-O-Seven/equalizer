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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

interface EqualsTestBase {

    ImmutableTestObject getTestObject();

    Equals<TestObject> getEquals();

    @Test
    default void givenValuesAreTheSameItShouldReturnTrue() {
        TestObject testObject = getTestObject();

        boolean result = getEquals().equals(testObject, testObject);

        assertThat(result)
                .isTrue();
    }

    @Test
    default void givenBothValuesAreNullItShouldReturnTrue() {
        boolean result = getEquals().equals(null, null);

        assertThat(result)
                .isTrue();
    }

    @Test
    default void givenOnlyFirstValueIsNullItShouldReturnFalse() {
        TestObject testObject = getTestObject();

        boolean result = getEquals().equals(null, testObject);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenOnlySecondValueIsNullItShouldReturnFalse() {
        TestObject testObject = getTestObject();

        boolean result = getEquals().equals(testObject, null);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenAllComparisonsSucceedingItShouldReturnTrue() {
        TestObject testObject1 = getTestObject();
        TestObject testObject2 = getTestObject();

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isTrue();
    }

    @Test
    default void givenIdentityComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject().withObjectValue(new Object());
        TestObject testObject2 = getTestObject().withObjectValue(new Object());

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenShallowComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject().withStringValue("foo");
        TestObject testObject2 = getTestObject().withStringValue("bar");

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenByteComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject().withByteValue((byte) 1234);
        TestObject testObject2 = getTestObject().withByteValue((byte) 5678);

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenShortComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject().withShortValue((short) 1234);
        TestObject testObject2 = getTestObject().withShortValue((short) 5678);

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenCharComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject().withCharValue('x');
        TestObject testObject2 = getTestObject().withCharValue('y');

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenIntComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject().withIntValue(1234);
        TestObject testObject2 = getTestObject().withIntValue(5678);

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenLongComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject().withLongValue(1234L);
        TestObject testObject2 = getTestObject().withLongValue(5678L);

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenFloatComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject().withFloatValue(1234.0f);
        TestObject testObject2 = getTestObject().withFloatValue(5678.0f);

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenDoubleComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject().withDoubleValue(1234.0);
        TestObject testObject2 = getTestObject().withDoubleValue(5678.0);

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @ParameterizedTest
    @CsvSource({
            "true, false",
            "false, true"
    })
    default void givenBooleanComparisonFailsItShouldReturnFalse(boolean booleanValue1, boolean booleanValue2) {
        TestObject testObject1 = getTestObject().withBooleanValue(booleanValue1);
        TestObject testObject2 = getTestObject().withBooleanValue(booleanValue2);

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenDeepComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject().withArrayValue("foo", "bar");
        TestObject testObject2 = getTestObject().withArrayValue("bla");

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    default void givenSuperComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject().withBaseObjectValue("foo");
        TestObject testObject2 = getTestObject().withBaseObjectValue("bar");

        boolean result = getEquals().equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

}
