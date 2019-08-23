package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HashImplTest {

    @Test
    void builderShouldBuildHashImpl() {
        Hash<String> hash = new HashImpl.Builder<String>().build();

        assertThat(hash)
                .isInstanceOf(HashImpl.class);
    }


    @Test
    void givenValueIsNullItShouldReturnZero() {
        Hash<TestData> hash = new HashImpl.Builder<TestData>().build();

        int result = hash.hashCode(null);

        assertThat(result)
                .isZero();
    }

    @Test
    void givenNoHashStepsItShouldReturnSystemIdentityHashCode() {
        TestData testData = new TestData("foo", new Object[0], 1337);
        Hash<TestData> hash = new HashImpl.Builder<TestData>().build();

        int result = hash.hashCode(testData);

        assertThat(result)
                .isEqualTo(System.identityHashCode(testData));
    }

    @Test
    void shouldComputeHash() {
        String stringValue = "foo";
        Object[] arrayValue = new Object[]{"test"};
        int intValue = 1337;
        TestData testData = new TestData(stringValue, arrayValue, intValue);
        Hash<TestData> hash = new HashImpl.Builder<TestData>()
                .withSuper(new HashImpl.Builder<BaseTestData>().hash(BaseTestData::getIntValue).build())
                .hash(TestData::getStringValue)
                .hashDeep(TestData::getArrayValue)
                .hashIdentity(TestData::getStringValue)
                .build();

        int result = hash.hashCode(testData);

        assertThat(result)
                .isEqualTo(31 *
                        (31 *
                                (31 *
                                        (31 +
                                                (31 + Integer.hashCode(intValue))
                                        ) + stringValue.hashCode()
                                ) + Arrays.deepHashCode(arrayValue)
                        ) + System.identityHashCode(stringValue));
    }

    private static class BaseTestData {

        private final int intValue;

        BaseTestData(int intValue) {
            this.intValue = intValue;
        }

        int getIntValue() {
            return intValue;
        }
    }

    private static class TestData extends BaseTestData {

        private final String stringValue;
        private final Object[] arrayValue;

        TestData(String stringValue, Object[] arrayValue, int intValue) {
            super(intValue);
            this.stringValue = stringValue;
            this.arrayValue = arrayValue;
        }

        String getStringValue() {
            return stringValue;
        }

        Object[] getArrayValue() {
            return arrayValue;
        }
    }
}