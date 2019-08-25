package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FloatHashStepTest {

    @Test
    void shouldReturnHashCode() {
        FloatHashStep<TestData> hashStep = new FloatHashStep<>(TestData::getValue);

        int result = hashStep.hash(new TestData(1337.0f));

        assertThat(result)
                .isEqualTo(Float.hashCode(1337.0f));
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