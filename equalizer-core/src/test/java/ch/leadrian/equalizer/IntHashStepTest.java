package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntHashStepTest {

    @Test
    void shouldReturnHashCode() {
        IntHashStep<TestData> hashStep = new IntHashStep<>(TestData::getValue);

        int result = hashStep.hash(new TestData(1337));

        assertThat(result)
                .isEqualTo(Integer.hashCode(1337));
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