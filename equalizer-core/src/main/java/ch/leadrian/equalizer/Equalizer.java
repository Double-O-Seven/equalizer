package ch.leadrian.equalizer;

public final class Equalizer {

    private Equalizer() {
        throw new UnsupportedOperationException();
    }

    public static <T> EqualsBuilder<T> equalsBuilder(Class<T> targetClass) {
        return new EqualsImpl.Builder<>(targetClass);
    }

    public static <T> HashCodeBuilder<T> hashCodeBuilder() {
        return new HashCodeImpl.Builder<>();
    }

    public static <T> EqualsAndHashCodeBuilder<T> equalsAndHashCodeBuilder(Class<T> targetClass) {
        return new EqualsAndHashCodeImpl.Builder<>(targetClass);
    }
}
