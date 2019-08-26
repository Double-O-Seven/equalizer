package ch.leadrian.equalizer;

import static java.util.Objects.requireNonNull;

/**
 * A class containing default {@link ClassMatcher} implementations. Specific instances can be instantiated using the given
 * factory methods.
 *
 * @see ClassMatcher
 */
public final class ClassMatchers {

    private ClassMatchers() {
    }

    /**
     * <p>
     * The default class matcher that is used for {@link Equals} and {@link EqualsAndHashCode} when configuring
     * instances with an {@link EqualsBuilder} or {@link EqualsAndHashCodeBuilder}.
     * </p>
     * <p>
     * Two values are considered to have matching classes of both are instances of {@code T}. Either by being direct instances
     * of by being instances of a subclass of {@code T}.
     * </p>
     * <p>
     * Using this matcher is recommended when equivalence between instances of different subclasses of {@code T}
     * should be possible.
     * </p>
     *
     * @param targetClass class of which two values must be instances of in order to have matching classes.
     * @param <T>         type represented by {@code targetClass}
     * @return {@link ClassMatcher} matching classes if both are instances of {@code targetClass}
     */
    public static <T> ClassMatcher<T> instanceOf(Class<T> targetClass) {
        return new InstanceOf<>(targetClass);
    }

    private static class InstanceOf<T> implements ClassMatcher<T> {

        private final Class<T> targetClass;

        InstanceOf(Class<T> targetClass) {
            requireNonNull(targetClass, "targetClass must not be null");
            this.targetClass = targetClass;
        }

        @Override
        public boolean classesMatch(T object, Object otherObject) {
            return targetClass.isInstance(otherObject);
        }
    }

    /**
     * <p>
     * Two values are considered to have matching classes if both are direct instances of {@code T}. If either value
     * is an instance of a subclass of {@code T}, equivalence will be impossible.
     * </p>
     * <p>
     * This match should NOT be used when {@link Equals} or {@link EqualsAndHashCode} are inherited as equivalence
     * cannot be satisfied for subclasses of {@code T}.
     * </p>
     *
     * @param targetClass class of which two values must be instances of in order to have matching classes.
     * @param <T>         type represented by {@code targetClass}
     * @return {@link ClassMatcher} matching classes if both are instances of {@code targetClass}
     */
    public static <T> ClassMatcher<T> exactClass(Class<T> targetClass) {
        return new ExactClass<>(targetClass);
    }

    private static class ExactClass<T> implements ClassMatcher<T> {

        private final Class<T> targetClass;

        ExactClass(Class<T> targetClass) {
            requireNonNull(targetClass, "targetClass must not be null");
            this.targetClass = targetClass;
        }

        @Override
        public boolean classesMatch(T object, Object otherObject) {
            return object.getClass() == targetClass && otherObject.getClass() == targetClass;
        }
    }


    /**
     * <p>
     * Two values are considered to have matching classes if both values are instances of exactly the same class, which
     * may be subclasses of {@code T}.
     * </p>
     * <p>
     * Using this {@link ClassMatcher} is recommended if {@link Equals} or {@link EqualsAndHashCode} of a class {@code T}
     * are reused in subclasses and equivalence between instances of different subclasses should not be possible.
     * </p>
     *
     * @param <T> type for which instances may be considered equivalent
     * @return {@link ClassMatcher} matching classes if both are instances of {@code targetClass}
     */
    @SuppressWarnings("unchecked")
    public static <T> ClassMatcher<T> sameClass() {
        return (ClassMatcher<T>) SameClass.INSTANCE;
    }

    private static class SameClass implements ClassMatcher<Object> {

        static final SameClass INSTANCE = new SameClass();

        private SameClass() {
        }

        @Override
        public boolean classesMatch(Object object, Object otherObject) {
            return object.getClass() == otherObject.getClass();
        }
    }

}
