package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EqualizerTest {

    @Test
    void equalsForShouldReturnEquivalenceImplBuilder() {
        EquivalenceBuilder<String> builder = Equalizer.equalsFor(String.class);

        assertThat(builder)
                .isInstanceOf(EquivalenceImpl.Builder.class);
    }

}