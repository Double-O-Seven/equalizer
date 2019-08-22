package ch.leadrian.equalizer;

public final class Equalizer {

    private Equalizer() {
        throw new UnsupportedOperationException();
    }

    public static <T> EquivalenceBuilder<T> equalsFor(Class<T> targetClass) {
        return new EquivalenceImpl.Builder<>(targetClass);
    }

    public static <T> HashBuilder<T> hashCodeFor(Class<T> targetClass) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    public static <T> EquivalenceAndHashBuilder<T> equalsAndHashCodeFor(Class<T> targetClass) {
        // TODO implement
        throw new UnsupportedOperationException();
    }
}
