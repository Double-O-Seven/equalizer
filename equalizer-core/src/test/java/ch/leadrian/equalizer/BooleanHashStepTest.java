package ch.leadrian.equalizer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BooleanHashStepTest {

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void shouldReturnHashCode(boolean value) {
        BooleanHashStep<TestData> hashStep = new BooleanHashStep<>(TestData::getValue);

        int result = hashStep.hash(new TestData(value));

        assertThat(result)
                .isEqualTo(Boolean.hashCode(value));
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