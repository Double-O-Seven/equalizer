package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ByteComparisonStepTest {

    @Test
    void givenValuesAreEqualItShouldReturnTrue() {
        ByteComparisonStep<TestData> hashStep = new ByteComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData((byte) 5), new TestData((byte) 5));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        ByteComparisonStep<TestData> hashStep = new ByteComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData((byte) 4), new TestData((byte) 5));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final byte value;

        TestData(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }

}