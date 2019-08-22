package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EquivalenceImplTest implements EquivalenceTest {

    @Override
    public <T> EquivalenceBuilder<T> newBuilder(Class<T> targetClass) {
        return new EquivalenceImpl.Builder<>(targetClass);
    }

    @Test
    void builderShouldBuildEquivalenceImpl() {
        Equivalence<EquivalenceTest.TestData> equivalence = new EquivalenceImpl.Builder<>(EquivalenceTest.TestData.class).build();

        assertThat(equivalence)
                .isInstanceOf(EquivalenceImpl.class);
    }

}