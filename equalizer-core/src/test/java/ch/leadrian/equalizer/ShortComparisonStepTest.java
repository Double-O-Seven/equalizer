package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShortComparisonStepTest {

    @Test
    void givenValuesAreEqualItShouldReturnTrue() {
        ShortComparisonStep<TestData> hashStep = new ShortComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData((short) 1234), new TestData((short) 1234));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        ShortComparisonStep<TestData> hashStep = new ShortComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData((short) 1234), new TestData((short) 1337));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final short value;

        TestData(short value) {
            this.value = value;
        }

        public short getValue() {
            return value;
        }
    }

}