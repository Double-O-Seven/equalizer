package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LongComparisonStepTest {

    @Test
    void givenValuesAreEqualItShouldReturnTrue() {
        LongComparisonStep<TestData> hashStep = new LongComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(1234L), new TestData(1234L));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        LongComparisonStep<TestData> hashStep = new LongComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(1234L), new TestData(1337L));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final long value;

        TestData(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }
    }

}