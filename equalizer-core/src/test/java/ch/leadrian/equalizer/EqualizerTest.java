package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EqualizerTest {

    @Test
    void equalsBuilderShouldReturnEquivalenceImplBuilder() {
        EquivalenceBuilder<String> builder = Equalizer.equalsBuilder(String.class);

        assertThat(builder)
                .isInstanceOf(EquivalenceImpl.Builder.class);
    }

    @Test
    void hashCodeBuilderShouldReturnHashImplBuilder() {
        HashBuilder<String> builder = Equalizer.hashCodeBuilder();

        assertThat(builder)
                .isInstanceOf(HashImpl.Builder.class);
    }

    @Test
    void equalsAndHashCodeBuilderShouldReturnEquivalenceAndHashImplBuilder() {
        EquivalenceAndHashBuilder<String> builder = Equalizer.equalsAndHashCodeBuilder(String.class);

        assertThat(builder)
                .isInstanceOf(EquivalenceAndHashImpl.Builder.class);
    }

}