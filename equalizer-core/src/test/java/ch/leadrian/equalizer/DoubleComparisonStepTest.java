package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoubleComparisonStepTest {

    @Test
    void givenValuesAreEqualItShouldReturnTrue() {
        DoubleComparisonStep<TestData> hashStep = new DoubleComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(1234.0), new TestData(1234.0));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        DoubleComparisonStep<TestData> hashStep = new DoubleComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(1234.0), new TestData(1337.0));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final double value;

        TestData(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }

}