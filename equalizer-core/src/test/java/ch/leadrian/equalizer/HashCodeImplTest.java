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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HashCodeImplTest {

    @Test
    void builderShouldBuildHashImpl() {
        HashCode<String> hashCode = new HashCodeImpl.Builder<String>().build();

        assertThat(hashCode)
                .isInstanceOf(HashCodeImpl.class);
    }


    @Test
    void givenValueIsNullItShouldReturnZero() {
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>().build();

        int result = hashCode.hashCode(null);

        assertThat(result)
                .isZero();
    }

    @Test
    void givenNoHashStepsItShouldReturnSystemIdentityHashCode() {
        TestObject testObject = testObject();
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>().build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(System.identityHashCode(testObject));
    }

    @Test
    void shouldCombineHashSteps() {
        TestObject testObject = testObject()
                .withIntValue(1337)
                .withStringValue("Test");
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hashPrimitive(TestObject::getIntValue)
                .hash(TestObject::getStringValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 * (31 + Integer.hashCode(1337)) + "Test".hashCode());
    }

    @Test
    void shouldUseStringHashStep() {
        TestObject testObject = testObject()
                .withStringValue("Test");
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hash(TestObject::getStringValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + "Test".hashCode());
    }

    @Test
    void shouldUseIdentityHashStep() {
        Object value = new Object();
        TestObject testObject = testObject()
                .withObjectValue(value);
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hashIdentity(TestObject::getObjectValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + System.identityHashCode(value));
    }

    @Test
    void shouldUseDeepHashStep() {
        TestObject testObject = testObject()
                .withArrayValue("foo", "bar");
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hashDeep(TestObject::getArrayValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + Arrays.deepHashCode(new Object[]{"foo", "bar"}));
    }

    @Test
    void shouldUseByteHashStep() {
        TestObject testObject = testObject()
                .withByteValue((byte) 5);
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hashPrimitive(TestObject::getByteValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + Byte.hashCode((byte) 5));
    }

    @Test
    void shouldUseShortHashStep() {
        TestObject testObject = testObject()
                .withShortValue((short) 5);
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hashPrimitive(TestObject::getShortValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + Short.hashCode((short) 5));
    }

    @Test
    void shouldUseCharHashStep() {
        TestObject testObject = testObject()
                .withCharValue('c');
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hashPrimitive(TestObject::getCharValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + Character.hashCode('c'));
    }

    @Test
    void shouldUseIntHashStep() {
        TestObject testObject = testObject()
                .withIntValue(1337);
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hashPrimitive(TestObject::getIntValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + Integer.hashCode(1337));
    }

    @Test
    void shouldUseLongHashStep() {
        TestObject testObject = testObject()
                .withLongValue(1337L);
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hashPrimitive(TestObject::getLongValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + Long.hashCode(1337L));
    }

    @Test
    void shouldUseFloatHashStep() {
        TestObject testObject = testObject()
                .withFloatValue(1337.0f);
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hashPrimitive(TestObject::getFloatValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + Float.hashCode(1337.0f));
    }

    @Test
    void shouldUseDoubleHashStep() {
        TestObject testObject = testObject()
                .withDoubleValue(1337.0);
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hashPrimitive(TestObject::getDoubleValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + Double.hashCode(1337.0));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void shouldUseBooleanHashStep(boolean booleanValue) {
        TestObject testObject = testObject()
                .withBooleanValue(booleanValue);
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hashPrimitive(TestObject::getBooleanValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + Boolean.hashCode(booleanValue));
    }

    private static ImmutableTestObject testObject() {
        return ImmutableTestObject
                .builder()
                .baseObjectValue("Base")
                .stringValue("Test")
                .objectValue(new Object())
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