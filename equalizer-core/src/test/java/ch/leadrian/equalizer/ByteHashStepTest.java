package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ByteHashStepTest {

    @Test
    void shouldReturnHashCode() {
        ByteHashStep<TestData> hashStep = new ByteHashStep<>(TestData::getValue);

        int result = hashStep.hash(new TestData((byte) 5));

        assertThat(result)
                .isEqualTo(Byte.hashCode((byte) 5));
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