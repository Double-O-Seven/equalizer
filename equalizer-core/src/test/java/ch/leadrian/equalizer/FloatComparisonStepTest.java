package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FloatComparisonStepTest {

    @ParameterizedTest
    @CsvSource({
            "1234.0, 1234.0",
            "NaN, NaN"
    })
    void givenValuesAreEqualItShouldReturnTrue(float value1, float value2) {
        FloatComparisonStep<TestData> hashStep = new FloatComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(value1), new TestData(value2));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        FloatComparisonStep<TestData> hashStep = new FloatComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(1234.0f), new TestData(1337.0f));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final float value;

        TestData(float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }
    }

}