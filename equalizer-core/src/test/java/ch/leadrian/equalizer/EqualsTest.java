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

class EqualsTest {

    private static Object OBJECT = new Object();

    @Test
    void givenEqualObjectsItShouldReturnTrue() {
        TestObject testObject1 = testObject();
        TestObject testObject2 = testObject();
        Equals<TestObject> equals = Equals.of(TestObject.class, TestObject::getStringValue, TestObject::getIntValue);

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "foo, bar, 1234, 1234",
            "foo, foo, 1234, 1337"
    })
    void givenNotEqualObjectsItShouldReturnFalse(String stringValue1, String stringValue2, int intValue1, int intValue2) {
        TestObject testObject1 = testObject()
                .withStringValue(stringValue1)
                .withIntValue(intValue1);
        TestObject testObject2 = testObject()
                .withStringValue(stringValue2)
                .withIntValue(intValue2);
        Equals<TestObject> equals = Equals.of(TestObject.class, TestObject::getStringValue, TestObject::getIntValue);

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    private static ImmutableTestObject testObject() {
        return ImmutableTestObject
                .builder()
                .baseObjectValue("Base")
                .stringValue("Test")
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