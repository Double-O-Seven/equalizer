package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EqualizerTest {

    @Test
    void equivalenceBuilderShouldReturnEquivalenceImplBuilder() {
        EquivalenceBuilder<String> builder = Equalizer.equivalenceBuilder(String.class);

        assertThat(builder)
                .isInstanceOf(EquivalenceImpl.Builder.class);
    }

}