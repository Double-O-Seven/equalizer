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
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EqualsImplTest implements EqualsTest {

    private static Object OBJECT = new Object();

    private Equals<TestObject> equals;

    @BeforeEach
    void setUp() {
        Equals<TestObjectBase> superEquals = new EqualsImpl.Builder<>(TestObjectBase.class)
                .compare(TestObjectBase::getBaseObjectValue)
                .build();
        equals = new EqualsImpl.Builder<>(TestObject.class)
                .withSuper(superEquals)
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

    @SuppressWarnings("StringOperationCanBeSimplified")
    @Override
    public ImmutableTestObject getTestObject() {
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

    @Override
    public Equals<TestObject> getEquals() {
        return equals;
    }

    @Test
    void builderShouldBuildEqualsImpl() {
        assertThat(equals)
                .isInstanceOf(EqualsImpl.class);
    }

    @Test
    void givenValuesAreTheSameAndNoComparisonsItShouldReturnTrue() {
        TestObject testObject = getTestObject();
        Equals<TestObject> equals = new EqualsImpl.Builder<>(TestObject.class).build();

        boolean result = equals.equals(testObject, testObject);

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotTheSameAndNoComparisonsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject();
        TestObject testObject2 = getTestObject();
        Equals<TestObject> equals = new EqualsImpl.Builder<>(TestObject.class).build();

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenClassesDoNotMatchItShouldReturnFalse() {
        TestObject testObject1 = getTestObject();
        TestObject testObject2 = getTestObject();
        Equals<TestObject> equals = new EqualsImpl.Builder<>(TestObject.class)
                .compare(TestObject::getStringValue)
                .classMatcher((v1, v2) -> false)
                .build();

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenDelegatingComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = getTestObject();
        TestObject testObject2 = getTestObject();
        Equals<TestObject> equals = new EqualsImpl.Builder<>(TestObject.class)
                .compare(TestObject::getStringValue)
                .equalIf((value1, value2) -> false)
                .build();

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

}