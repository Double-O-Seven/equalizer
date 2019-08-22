package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DelegatingHashStepTest {

    @Test
    void shouldReturnHashCodeOfDelegate() {
        DelegatingHashStep<String> hashStep = new DelegatingHashStep<>(value -> 1337);

        int result = hashStep.hash("test");

        assertThat(result)
                .isEqualTo(1337);
    }

}