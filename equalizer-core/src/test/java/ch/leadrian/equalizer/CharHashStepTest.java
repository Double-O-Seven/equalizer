package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CharHashStepTest {

    @Test
    void shouldReturnHashCode() {
        CharHashStep<TestData> hashStep = new CharHashStep<>(TestData::getValue);

        int result = hashStep.hash(new TestData('c'));

        assertThat(result)
                .isEqualTo(Character.hashCode('c'));
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