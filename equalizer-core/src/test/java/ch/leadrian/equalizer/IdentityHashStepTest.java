package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IdentityHashStepTest {

    @Test
    void shouldReturnSystemIdentityHashCode() {
        String value = "test";
        TestData testData = new TestData(value);
        IdentityHashStep<TestData> identityHashStep = new IdentityHashStep<>(TestData::getValue);

        int hashCode = identityHashStep.hash(testData);

        assertThat(hashCode)
                .isEqualTo(System.identityHashCode(value));
    }

    private static class TestData {

        private final String value;

        TestData(String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }
    }

}