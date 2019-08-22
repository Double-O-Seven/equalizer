package ch.leadrian.equalizer;

public final class Equalizer {

    private Equalizer() {
        throw new UnsupportedOperationException();
    }

    public static <T> EquivalenceBuilder<T> equalsFor(Class<T> clazz) {
        return new EquivalenceImpl.Builder<>(clazz);
    }

    public static <T> HashBuilder<T> hashCodeFor(Class<T> clazz) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    public static <T> EquivalenceAndHashBuilder<T> equalsAndHashCodeFor(Class<T> clazz) {
        // TODO implement
        throw new UnsupportedOperationException();
    }
}
