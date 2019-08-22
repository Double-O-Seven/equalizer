package ch.leadrian.equalizer;

public final class Equalizer {

    private Equalizer() {
        throw new UnsupportedOperationException();
    }

    public static <T> EquivalenceBuilder<T> equivalenceBuilder(Class<T> targetClass) {
        return new EquivalenceImpl.Builder<>(targetClass);
    }

    public static <T> HashBuilder<T> hashBuilder() {
        return new HashImpl.Builder<>();
    }

    public static <T> EquivalenceAndHashBuilder<T> equivalenceAndHashBuilder(Class<T> targetClass) {
        // TODO implement
        throw new UnsupportedOperationException();
    }
}
