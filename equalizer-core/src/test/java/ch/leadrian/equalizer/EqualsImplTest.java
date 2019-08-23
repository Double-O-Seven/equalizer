package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EqualsImplTest {

    @Test
    void builderShouldBuildEqualsImpl() {
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class).build();

        assertThat(equals)
                .isInstanceOf(EqualsImpl.class);
    }

    @Test
    void givenValuesAreTheSameItShouldReturnTrue() {
        TestData testData = new TestData("test", 1, new Object[0], "base");
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class).build();

        boolean result = equals.equals(testData, testData);

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenBothValuesAreNullItShouldReturnTrue() {
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class).build();

        boolean result = equals.equals(null, null);

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenFirstValueIsNullItShouldReturnFalse() {
        TestData testData = new TestData("test", 1, new Object[0], "base");
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class).build();

        boolean result = equals.equals(null, testData);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenSecondValueIsNullItShouldReturnFalse() {
        TestData testData = new TestData("test", 1, new Object[0], "base");
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class).build();

        boolean result = equals.equals(testData, null);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenDifferentValuesWithNoComparisonsItShouldReturnFalse() {
        TestData testData1 = new TestData("test", 1, new Object[0], "base");
        TestData testData2 = new TestData("test", 1, new Object[0], "base");
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class).build();

        boolean result = equals.equals(testData1, testData2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenAllComparisonsSucceedingItShouldReturnTrue() {
        String stringValue = "test";
        TestData testData1 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
        TestData testData2 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class)
                .withSuper(new EqualsImpl.Builder<>(BaseTestData.class).compare(BaseTestData::getSuperStringValue).build())
                .compareIdentity(TestData::getStringValue)
                .compare(TestData::getIntValue)
                .compareDeep(TestData::getArrayValue)
                .equalIf((value1, value2) -> true)
                .build();

        boolean result = equals.equals(testData1, testData2);

        assertThat(result)
                .isTrue();
    }

    @SuppressWarnings("StringOperationCanBeSimplified")
    @Test
    void givenIdentityComparisonFailsItShouldReturnFalse() {
        TestData testData1 = new TestData("test", 65536, new Object[]{"bla"}, "base");
        TestData testData2 = new TestData(new String("test"), 65536, new Object[]{"bla"}, "base");
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class)
                .withSuper(new EqualsImpl.Builder<>(BaseTestData.class).compare(BaseTestData::getSuperStringValue).build())
                .compareIdentity(TestData::getStringValue)
                .compare(TestData::getIntValue)
                .compareDeep(TestData::getArrayValue)
                .equalIf((value1, value2) -> true)
                .build();

        boolean result = equals.equals(testData1, testData2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenShallowComparisonFailsItShouldReturnFalse() {
        String stringValue = "test";
        TestData testData1 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
        TestData testData2 = new TestData(stringValue, 1234, new Object[]{"bla"}, "base");
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class)
                .withSuper(new EqualsImpl.Builder<>(BaseTestData.class).compare(BaseTestData::getSuperStringValue).build())
                .compareIdentity(TestData::getStringValue)
                .compare(TestData::getIntValue)
                .compareDeep(TestData::getArrayValue)
                .equalIf((value1, value2) -> true)
                .build();

        boolean result = equals.equals(testData1, testData2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenDeepComparisonFailsItShouldReturnFalse() {
        String stringValue = "test";
        TestData testData1 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
        TestData testData2 = new TestData(stringValue, 65536, new Object[]{"blub"}, "base");
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class)
                .withSuper(new EqualsImpl.Builder<>(BaseTestData.class).compare(BaseTestData::getSuperStringValue).build())
                .compareIdentity(TestData::getStringValue)
                .compare(TestData::getIntValue)
                .compareDeep(TestData::getArrayValue)
                .equalIf((value1, value2) -> true)
                .build();

        boolean result = equals.equals(testData1, testData2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenDelegatingComparisonFailsItShouldReturnFalse() {
        String stringValue = "test";
        TestData testData1 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
        TestData testData2 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class)
                .withSuper(new EqualsImpl.Builder<>(BaseTestData.class).compare(BaseTestData::getSuperStringValue).build())
                .compareIdentity(TestData::getStringValue)
                .compare(TestData::getIntValue)
                .compareDeep(TestData::getArrayValue)
                .equalIf((value1, value2) -> false)
                .build();

        boolean result = equals.equals(testData1, testData2);

        assertThat(result)
                .isFalse();
    }

    @Test
    void givenSuperComparisonFailsItShouldReturnFalse() {
        String stringValue = "test";
        TestData testData1 = new TestData(stringValue, 65536, new Object[]{"bla"}, "base");
        TestData testData2 = new TestData(stringValue, 65536, new Object[]{"bla"}, "haha");
        Equals<TestData> equals = new EqualsImpl.Builder<>(TestData.class)
                .withSuper(new EqualsImpl.Builder<>(BaseTestData.class).compare(BaseTestData::getSuperStringValue).build())
                .compareIdentity(TestData::getStringValue)
                .compare(TestData::getIntValue)
                .compareDeep(TestData::getArrayValue)
                .equalIf((value1, value2) -> true)
                .build();

        boolean result = equals.equals(testData1, testData2);

        assertThat(result)
                .isFalse();
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