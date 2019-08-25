package ch.leadrian.equalizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class EqualsImplTest {

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

    @Test
    void builderShouldBuildEqualsImpl() {
        assertThat(equals)
                .isInstanceOf(EqualsImpl.class);
    }

    @Test
    void givenValuesAreTheSameItShouldReturnTrue() {
        TestObject testObject = testObject();

        boolean result = equals.equals(testObject, testObject);

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
        boolean result = equals.equals(null, null);

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenOnlyFirstValueIsNullItShouldReturnFalse() {
        TestObject testObject = testObject();

        boolean result = equals.equals(null, testObject);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenOnlySecondValueIsNullItShouldReturnFalse() {
        TestObject testObject = testObject();

        boolean result = equals.equals(testObject, null);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenAllComparisonsSucceedingItShouldReturnTrue() {
        TestObject testObject1 = testObject();
        TestObject testObject2 = testObject();

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenIdentityComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject().withObjectValue(new Object());
        TestObject testObject2 = testObject().withObjectValue(new Object());

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenShallowComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject().withStringValue("foo");
        TestObject testObject2 = testObject().withStringValue("bar");

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenByteComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject().withByteValue((byte) 1234);
        TestObject testObject2 = testObject().withByteValue((byte) 5678);

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenShortComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject().withShortValue((short) 1234);
        TestObject testObject2 = testObject().withShortValue((short) 5678);

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenCharComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject().withCharValue('x');
        TestObject testObject2 = testObject().withCharValue('y');

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenIntComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject().withIntValue(1234);
        TestObject testObject2 = testObject().withIntValue(5678);

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenLongComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject().withLongValue(1234L);
        TestObject testObject2 = testObject().withLongValue(5678L);

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenFloatComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject().withFloatValue(1234.0f);
        TestObject testObject2 = testObject().withFloatValue(5678.0f);

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenDoubleComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject().withDoubleValue(1234.0);
        TestObject testObject2 = testObject().withDoubleValue(5678.0);

        boolean result = equals.equals(testObject1, testObject2);

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

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenDeepComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject().withArrayValue("foo", "bar");
        TestObject testObject2 = testObject().withArrayValue("bla");

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenDelegatingComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject();
        TestObject testObject2 = testObject();
        Equals<TestObject> equals = new EqualsImpl.Builder<>(TestObject.class)
                .compare(TestObject::getStringValue)
                .equalIf((value1, value2) -> false)
                .build();

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenSuperComparisonFailsItShouldReturnFalse() {
        TestObject testObject1 = testObject().withBaseObjectValue("foo");
        TestObject testObject2 = testObject().withBaseObjectValue("bar");

        boolean result = equals.equals(testObject1, testObject2);

        assertThat(result)
                .isFalse();
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