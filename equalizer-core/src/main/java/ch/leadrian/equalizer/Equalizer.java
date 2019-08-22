package ch.leadrian.equalizer;

public final class Equalizer {

    private Equalizer() {
        throw new UnsupportedOperationException();
    }

    public static <T> EquivalenceBuilder<T> equalsBuilder(Class<T> targetClass) {
        return new EquivalenceImpl.Builder<>(targetClass);
    }

    public static <T> HashBuilder<T> hashCodeBuilder() {
        return new HashImpl.Builder<>();
    }

    public static <T> EquivalenceAndHashBuilder<T> equalsAndHashCodeBuilder(Class<T> targetClass) {
        return new EquivalenceAndHashImpl.Builder<>(targetClass);
    }
}
