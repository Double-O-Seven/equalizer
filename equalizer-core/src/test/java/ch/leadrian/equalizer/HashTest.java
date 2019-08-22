package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

interface HashTest {

    <T> HashBuilder<T> newBuilder();

    @Test
    default void givenValueIsNullItShouldReturnZero() {
        Hash<TestData> hash = this.<TestData>newBuilder().build();

        int result = hash.hashCode(null);

        assertThat(result)
                .isZero();
    }

    @Test
    default void givenNoHashStepsItShouldReturnSystemIdentityHashCode() {
        TestData testData = new TestData("foo", new Object[0], 1337);
        Hash<TestData> hash = this.<TestData>newBuilder().build();

        int result = hash.hashCode(testData);

        assertThat(result)
                .isEqualTo(System.identityHashCode(testData));
    }

    @Test
    default void shouldComputeHash() {
        String stringValue = "foo";
        Object[] arrayValue = new Object[]{"test"};
        int intValue = 1337;
        TestData testData = new TestData(stringValue, arrayValue, intValue);
        Hash<TestData> hash = this.<TestData>newBuilder()
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

    class BaseTestData {

        private final int intValue;

        BaseTestData(int intValue) {
            this.intValue = intValue;
        }

        public int getIntValue() {
            return intValue;
        }
    }

    class TestData extends BaseTestData {

        private final String stringValue;
        private final Object[] arrayValue;

        public TestData(String stringValue, Object[] arrayValue, int intValue) {
            super(intValue);
            this.stringValue = stringValue;
            this.arrayValue = arrayValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        public Object[] getArrayValue() {
            return arrayValue;
        }
    }

}