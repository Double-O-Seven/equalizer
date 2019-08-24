package ch.leadrian.equalizer;

/**
 * Class containing factory methods for builders.
 *
 * @see EqualsBuilder
 * @see HashCodeBuilder
 * @see EqualsAndHashCodeBuilder
 */
public final class Equalizer {

    private Equalizer() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param targetClass The class for which {@link Equals} should be used to implement {@link Object#equals(Object)}
     * @param <T>         Type of {@code targetClass}
     * @return {@link EqualsBuilder} for class {@code targetClass}
     */
    public static <T> EqualsBuilder<T> equalsBuilder(Class<T> targetClass) {
        return new EqualsImpl.Builder<>(targetClass);
    }

    /**
     * @param <T> Type of the class for which {@link HashCode} should be used to implement {@link Object#hashCode()}
     * @return {@link EqualsBuilder} for type {@code T}
     */
    public static <T> HashCodeBuilder<T> hashCodeBuilder() {
        return new HashCodeImpl.Builder<>();
    }

    /**
     * @param targetClass The class for which {@link EqualsAndHashCode} should be used to
     *                    implement {@link Object#equals(Object)} and {@link Object#hashCode()}
     * @param <T>         Type of {@code targetClass}
     * @return {@link EqualsAndHashCodeBuilder} for class {@code targetClass}
     */
    public static <T> EqualsAndHashCodeBuilder<T> equalsAndHashCodeBuilder(Class<T> targetClass) {
        return new EqualsAndHashCodeImpl.Builder<>(targetClass);
    }
}
