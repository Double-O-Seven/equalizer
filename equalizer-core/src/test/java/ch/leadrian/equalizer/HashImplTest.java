package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HashImplTest implements HashTest {

    @Override
    public <T> HashBuilder<T> newBuilder() {
        return new HashImpl.Builder<>();
    }

    @Test
    void builderShouldBuildHashImpl() {
        Hash<String> hash = new HashImpl.Builder<String>().build();

        assertThat(hash)
                .isInstanceOf(HashImpl.class);
    }
}