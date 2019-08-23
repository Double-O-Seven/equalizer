package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BooleanComparisonStepTest {

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void givenValuesAreEqualItShouldReturnTrue(boolean value) {
        BooleanComparisonStep<TestData> hashStep = new BooleanComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(value), new TestData(value));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        BooleanComparisonStep<TestData> hashStep = new BooleanComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(false), new TestData(true));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final boolean value;

        TestData(boolean value) {
            this.value = value;
        }

        public boolean getValue() {
            return value;
        }
    }

}