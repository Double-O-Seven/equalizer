package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoubleHashStepTest {

    @Test
    void shouldReturnHashCode() {
        DoubleHashStep<TestData> hashStep = new DoubleHashStep<>(TestData::getValue);

        int result = hashStep.hash(new TestData(1337.0));

        assertThat(result)
                .isEqualTo(Double.hashCode(1337.0));
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