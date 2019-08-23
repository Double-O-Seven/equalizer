package ch.leadrian.equalizer;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class EqualsAndHashCodeImplTest {

    @Test
    void builderShouldBuildEqualsAndHashCodeImpl() {
        EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class).build();

        assertThat(equalsAndHashCode)
                .isInstanceOf(EqualsAndHashCodeImpl.class);
    }

    @Nested
    class EqualsTests {

        @Test
        void givenValuesAreTheSameItShouldReturnTrue() {
            TestData testData = new TestData("test", 1, new Object[0], "base");
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class).build();

            boolean result = equalsAndHashCode.equals(testData, testData);

            assertThat(result)
                    .isTrue();
        }

        @Test
        void givenBothValuesAreNullItShouldReturnTrue() {
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class).build();

            boolean result = equalsAndHashCode.equals(null, null);

            assertThat(result)
                    .isTrue();
        }

        @Test
        void givenFirstValueIsNullItShouldReturnFalse() {
            TestData testData = new TestData("test", 1, new Object[0], "base");
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class).build();

            boolean result = equalsAndHashCode.equals(null, testData);

            assertThat(result)
                    .isFalse();
        }

        @Test
        void givenSecondValueIsNullItShouldReturnFalse() {
            TestData testData = new TestData("test", 1, new Object[0], "base");
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class).build();

            boolean result = equalsAndHashCode.equals(testData, null);

            assertThat(result)
                    .isFalse();
        }

        @Test
        void givenDifferentValuesWithNoComparisonsItShouldReturnFalse() {
            TestData testData1 = new TestData("test", 1, new Object[0], "base");
            TestData testData2 = new TestData("test", 1, new Object[0], "base");
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class).build();

            boolean result = equalsAndHashCode.equals(testData1, testData2);

            assertThat(result)
                    .isFalse();
        }

        @Test
        void givenAllComparisonsSucceedingItShouldReturnTrueAndHashCodesShouldBeTheSame() {
            String stringValue = "test";
            TestData testData1 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
            TestData testData2 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class)
                    .withSuper(new EqualsAndHashCodeImpl.Builder<>(BaseTestData.class).compareAndHash(BaseTestData::getSuperStringValue).build())
                    .compareAndHashIdentity(TestData::getStringValue)
                    .compareAndHash(TestData::getIntValue)
                    .compareAndHashDeep(TestData::getArrayValue)
                    .equalIf((value1, value2) -> true)
                    .build();

            boolean result = equalsAndHashCode.equals(testData1, testData2);
            int hashCode1 = equalsAndHashCode.hashCode(testData1);
            int hashCode2 = equalsAndHashCode.hashCode(testData2);

            assertAll(
                    () -> assertThat(result).isTrue(),
                    () -> assertThat(hashCode1).isEqualTo(hashCode2)
            );
        }

        @SuppressWarnings("StringOperationCanBeSimplified")
        @Test
        void givenIdentityComparisonFailsItShouldReturnFalse() {
            TestData testData1 = new TestData("test", 65536, new Object[]{"bla"}, "base");
            TestData testData2 = new TestData(new String("test"), 65536, new Object[]{"bla"}, "base");
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class)
                    .withSuper(new EqualsAndHashCodeImpl.Builder<>(BaseTestData.class).compare(BaseTestData::getSuperStringValue).build())
                    .compareIdentity(TestData::getStringValue)
                    .compare(TestData::getIntValue)
                    .compareDeep(TestData::getArrayValue)
                    .equalIf((value1, value2) -> true)
                    .build();

            boolean result = equalsAndHashCode.equals(testData1, testData2);

            assertThat(result)
                    .isFalse();
        }

        @Test
        void givenShallowComparisonFailsItShouldReturnFalse() {
            String stringValue = "test";
            TestData testData1 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
            TestData testData2 = new TestData(stringValue, 1234, new Object[]{"bla"}, "base");
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class)
                    .withSuper(new EqualsAndHashCodeImpl.Builder<>(BaseTestData.class).compare(BaseTestData::getSuperStringValue).build())
                    .compareIdentity(TestData::getStringValue)
                    .compare(TestData::getIntValue)
                    .compareDeep(TestData::getArrayValue)
                    .equalIf((value1, value2) -> true)
                    .build();

            boolean result = equalsAndHashCode.equals(testData1, testData2);

            assertThat(result)
                    .isFalse();
        }

        @Test
        void givenDeepComparisonFailsItShouldReturnFalse() {
            String stringValue = "test";
            TestData testData1 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
            TestData testData2 = new TestData(stringValue, 65536, new Object[]{"blub"}, "base");
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class)
                    .withSuper(new EqualsAndHashCodeImpl.Builder<>(BaseTestData.class).compare(BaseTestData::getSuperStringValue).build())
                    .compareIdentity(TestData::getStringValue)
                    .compare(TestData::getIntValue)
                    .compareDeep(TestData::getArrayValue)
                    .equalIf((value1, value2) -> true)
                    .build();

            boolean result = equalsAndHashCode.equals(testData1, testData2);

            assertThat(result)
                    .isFalse();
        }

        @Test
        void givenDelegatingComparisonFailsItShouldReturnFalse() {
            String stringValue = "test";
            TestData testData1 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
            TestData testData2 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class)
                    .withSuper(new EqualsAndHashCodeImpl.Builder<>(BaseTestData.class).compare(BaseTestData::getSuperStringValue).build())
                    .compareIdentity(TestData::getStringValue)
                    .compare(TestData::getIntValue)
                    .compareDeep(TestData::getArrayValue)
                    .equalIf((value1, value2) -> false)
                    .build();

            boolean result = equalsAndHashCode.equals(testData1, testData2);

            assertThat(result)
                    .isFalse();
        }

        @Test
        void givenSuperComparisonFailsItShouldReturnFalse() {
            String stringValue = "test";
            TestData testData1 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
            TestData testData2 = new TestData(stringValue, 65536, new Object[]{"bla"}, "haha");
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class)
                    .withSuper(new EqualsAndHashCodeImpl.Builder<>(BaseTestData.class).compare(BaseTestData::getSuperStringValue).build())
                    .compareIdentity(TestData::getStringValue)
                    .compare(TestData::getIntValue)
                    .compareDeep(TestData::getArrayValue)
                    .equalIf((value1, value2) -> true)
                    .build();

            boolean result = equalsAndHashCode.equals(testData1, testData2);

            assertThat(result)
                    .isFalse();
        }

    }

    @Nested
    class HashCodeTests {

        @Test
        void givenValueIsNullItShouldReturnZero() {
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class).build();

            int result = equalsAndHashCode.hashCode(null);

            assertThat(result)
                    .isZero();
        }

        @Test
        void givenNoHashStepsItShouldReturnSystemIdentityHashCode() {
            TestData testData = new TestData("foo", 1337, new Object[0], "bar");
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class).build();

            int result = equalsAndHashCode.hashCode(testData);

            assertThat(result)
                    .isEqualTo(System.identityHashCode(testData));
        }

        @Test
        void shouldComputeHash() {
            String stringValue = "foo";
            Object[] arrayValue = new Object[]{"test"};
            int intValue = 1337;
            String baseStringValue = "bar";
            TestData testData = new TestData(stringValue, intValue, arrayValue, baseStringValue);
            EqualsAndHashCode<TestData> equalsAndHashCode = new EqualsAndHashCodeImpl.Builder<>(TestData.class)
                    .withSuper(new EqualsAndHashCodeImpl.Builder<>(BaseTestData.class).compareAndHash(BaseTestData::getSuperStringValue).build())
                    .compareAndHash(TestData::getStringValue)
                    .compareAndHashDeep(TestData::getArrayValue)
                    .compareAndHashIdentity(TestData::getStringValue)
                    .build();

            int result = equalsAndHashCode.hashCode(testData);

            assertThat(result)
                    .isEqualTo(31 *
                            (31 *
                                    (31 *
                                            (31 +
                                                    (31 + baseStringValue.hashCode())
                                            ) + stringValue.hashCode()
                                    ) + Arrays.deepHashCode(arrayValue)
                            ) + System.identityHashCode(stringValue));
        }

    }

    private static class BaseTestData {

        private final String superStringValue;

        BaseTestData(String superStringValue) {
            this.superStringValue = superStringValue;
        }

        String getSuperStringValue() {
            return superStringValue;
        }

    }

    private static class TestData extends BaseTestData {

        private final String stringValue;
        private final int intValue;
        private final Object[] arrayValue;

        TestData(String stringValue, int intValue, Object[] arrayValue, String baseStringValue) {
            super(baseStringValue);
            this.stringValue = stringValue;
            this.intValue = intValue;
            this.arrayValue = arrayValue;
        }

        String getStringValue() {
            return stringValue;
        }

        int getIntValue() {
            return intValue;
        }

        Object[] getArrayValue() {
            return arrayValue;
        }
    }

}