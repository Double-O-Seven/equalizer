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
                .hash(TestObject::getIntValue)
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
    void shouldUseIntHashStep() {
        TestObject testObject = testObject()
                .withIntValue(1337);
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hash(TestObject::getIntValue)
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
                .hash(TestObject::getLongValue)
                .build();

        int result = hashCode.hashCode(testObject);

        assertThat(result)
                .isEqualTo(31 + Long.hashCode(1337L));
    }

    @Test
    void shouldUseDoubleHashStep() {
        TestObject testObject = testObject()
                .withDoubleValue(1337.0);
        HashCode<TestObject> hashCode = new HashCodeImpl.Builder<TestObject>()
                .hash(TestObject::getDoubleValue)
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
                .hash(TestObject::getBooleanValue)
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
                .intValue(1337)
                .longValue(1234L)
                .doubleValue(0.815)
                .booleanValue(true)
                .build();
    }
}