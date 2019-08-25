package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShortHashStepTest {

    @Test
    void shouldReturnHashCode() {
        ShortHashStep<TestData> hashStep = new ShortHashStep<>(TestData::getValue);

        int result = hashStep.hash(new TestData((short) 1337));

        assertThat(result)
                .isEqualTo(Short.hashCode((short) 1337));
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