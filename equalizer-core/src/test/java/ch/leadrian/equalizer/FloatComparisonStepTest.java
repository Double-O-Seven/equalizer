package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FloatComparisonStepTest {

    @Test
    void givenValuesAreEqualItShouldReturnTrue() {
        FloatComparisonStep<TestData> hashStep = new FloatComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(1234.0f), new TestData(1234.0f));

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