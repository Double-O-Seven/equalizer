package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LongHashStepTest {

    @Test
    void shouldReturnHashCode() {
        LongHashStep<TestData> hashStep = new LongHashStep<>(TestData::getValue);

        int result = hashStep.hash(new TestData(1337L));

        assertThat(result)
                .isEqualTo(Long.hashCode(1337L));
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