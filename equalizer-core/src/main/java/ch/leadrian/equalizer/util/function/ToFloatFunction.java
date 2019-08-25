package ch.leadrian.equalizer.util.function;

/**
 * Represents a function that produces a float-valued result.  This is the
 * {@code float}-producing primitive specialization for {@link java.util.function.Function},
 * which is missing in the JDK. To avoid any auto-boxing while comparing or hashing object attributes,
 * this function has been added to Equalizer for sake of completeness.
 *
 * @param <T> the type of the input to the function
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface ToFloatFunction<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    float applyAsFloat(T value);

}
