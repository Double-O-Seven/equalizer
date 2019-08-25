package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CharComparisonStepTest {

    @Test
    void givenValuesAreEqualItShouldReturnTrue() {
        CharComparisonStep<TestData> hashStep = new CharComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData('c'), new TestData('c'));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        CharComparisonStep<TestData> hashStep = new CharComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData('a'), new TestData('b'));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final char value;

        TestData(char value) {
            this.value = value;
        }

        public char getValue() {
            return value;
        }
    }

}