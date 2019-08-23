package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EqualizerTest {

    @Test
    void equalsBuilderShouldReturnEqualsImplBuilder() {
        EqualsBuilder<String> builder = Equalizer.equalsBuilder(String.class);

        assertThat(builder)
                .isInstanceOf(EqualsImpl.Builder.class);
    }

    @Test
    void hashCodeBuilderShouldReturnHashImplBuilder() {
        HashCodeBuilder<String> builder = Equalizer.hashCodeBuilder();

        assertThat(builder)
                .isInstanceOf(HashCodeImpl.Builder.class);
    }

    @Test
    void equalsAndHashCodeBuilderShouldReturnEqualsAndHashCodeImplBuilder() {
        EqualsAndHashCodeBuilder<String> builder = Equalizer.equalsAndHashCodeBuilder(String.class);

        assertThat(builder)
                .isInstanceOf(EqualsAndHashCodeImpl.Builder.class);
    }

}