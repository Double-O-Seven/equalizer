package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShallowHashStepTest {

    @Test
    void givenNullItShouldReturnZero() {
        TestData testData = new TestData(null);
        ShallowHashStep<TestData> hashStep = new ShallowHashStep<>(TestData::getValue);

        int hashCode = hashStep.hash(testData);

        assertThat(hashCode)
                .isZero();
    }

    @Test
    void givenNonNullValueItShouldReturnHashCodeOfValue() {
        String value = "test";
        TestData testData = new TestData(value);
        ShallowHashStep<TestData> hashStep = new ShallowHashStep<>(TestData::getValue);

        int hashCode = hashStep.hash(testData);

        assertThat(hashCode)
                .isEqualTo(value.hashCode());
    }

    private static class TestData {

        private final String value;

        TestData(String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }
    }

}