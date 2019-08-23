package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntComparisonStepTest {

    @Test
    void givenValuesAreEqualItShouldReturnTrue() {
        IntComparisonStep<TestData> hashStep = new IntComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(1234), new TestData(1234));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        IntComparisonStep<TestData> hashStep = new IntComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(1234), new TestData(1337));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final int value;

        TestData(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}