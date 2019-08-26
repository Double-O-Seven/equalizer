package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToByteFunction;
import ch.leadrian.equalizer.util.function.ToCharFunction;
import ch.leadrian.equalizer.util.function.ToFloatFunction;
import ch.leadrian.equalizer.util.function.ToShortFunction;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * A builder for {@link Equals}. It is used to configure which attributes of type {@link T} are used to
 * determine equality between two instances of type {@code T}.
 *
 * @param <T> type for which an {@link Equals} instance should be built
 * @see Equals
 */
public interface EqualsBuilder<T> {

    /**
     * If the equivalence check performed by {@code superEquals} is successful, then two instance of type {@code T}
     * are equal, if all other equivalence checks succeed as well, else, if equivalence is not given by {@code superEquals},
     * two instances of type {@code T} are not considered equal.
     *
     * @param superEquals {@link Equals} instance used to compute the equivalence for a supertype of {@code T}.
     * @return {@code this}
     */
    EqualsBuilder<T> withSuper(Equals<? super T> superEquals);

    /**
     * Configure a class matcher. By default, {@link ClassMatchers#instanceOf(Class)} is used. You may override
     * this using this method.
     *
     * @param classMatcher class matcher used to determine whether two instances of {@code T} can be compared
     * @return {@code this}
     * @see ClassMatcher
     * @see ClassMatchers
     */
    EqualsBuilder<T> classMatcher(ClassMatcher<T> classMatcher);

    /**
     * Configures an {@link Equals} instance to compare object values extracted by {@code valueExtractor} to determine the
     * equivalence between two instances of type {@code T}.
     * The value passed to {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     */
    EqualsBuilder<T> compare(Function<? super T, ?> valueExtractor);

    /**
     * Configures an {@link Equals} instance to compare byte values extracted by {@code valueExtractor} to determine the
     * equivalence between two instances of type {@code T}.
     * The value passed to {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor Extracts a byte value of an instance of type {@code T}.
     * @return {@code this}
     */
    EqualsBuilder<T> comparePrimitive(ToByteFunction<? super T> valueExtractor);

    /**
     * Configures an {@link Equals} instance to compare short values extracted by {@code valueExtractor} to determine the
     * equivalence between two instances of type {@code T}.
     * The value passed to {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor Extracts a short value of an instance of type {@code T}.
     * @return {@code this}
     */
    EqualsBuilder<T> comparePrimitive(ToShortFunction<? super T> valueExtractor);

    /**
     * Configures an {@link Equals} instance to compare character values extracted by {@code valueExtractor} to determine the
     * equivalence between two instances of type {@code T}.
     * The value passed to {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor Extracts a character value of an instance of type {@code T}.
     * @return {@code this}
     */
    EqualsBuilder<T> comparePrimitive(ToCharFunction<? super T> valueExtractor);

    /**
     * Configures an {@link Equals} instance to compare integer values extracted by {@code valueExtractor} to determine the
     * equivalence between two instances of type {@code T}.
     * The value passed to {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor Extracts a integer value of an instance of type {@code T}.
     * @return {@code this}
     */
    EqualsBuilder<T> comparePrimitive(ToIntFunction<? super T> valueExtractor);

    /**
     * Configures an {@link Equals} instance to compare long values extracted by {@code valueExtractor} to determine the
     * equivalence between two instances of type {@code T}.
     * The value passed to {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor Extracts a long value of an instance of type {@code T}.
     * @return {@code this}
     */
    EqualsBuilder<T> comparePrimitive(ToLongFunction<? super T> valueExtractor);

    /**
     * Configures an {@link Equals} instance to compare float values extracted by {@code valueExtractor} to determine the
     * equivalence between two instances of type {@code T}.
     * The value passed to {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor Extracts a float value of an instance of type {@code T}.
     * @return {@code this}
     */
    EqualsBuilder<T> comparePrimitive(ToFloatFunction<? super T> valueExtractor);

    /**
     * Configures an {@link Equals} instance to compare double values extracted by {@code valueExtractor} to determine the
     * equivalence between two instances of type {@code T}.
     * The value passed to {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor Extracts a double value of an instance of type {@code T}.
     * @return {@code this}
     */
    EqualsBuilder<T> comparePrimitive(ToDoubleFunction<? super T> valueExtractor);

    /**
     * Configures an {@link Equals} instance to compare boolean values extracted by {@code valueExtractor} to determine the
     * equivalence between two instances of type {@code T}.
     * The value passed to {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor Extracts a boolean value of an instance of type {@code T}.
     * @return {@code this}
     */
    EqualsBuilder<T> comparePrimitive(Predicate<? super T> valueExtractor);

    /**
     * Configures an {@link Equals} instance to compare object values extracted by {@code valueExtractor} to determine the
     * equivalence between two instances of type {@code T}.
     * This comparison should in general be used to compare the contents of arrays, object or primitives. If a non-array
     * object is passed, a default object equivalence check will be performed.
     * The value passed to {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor Extracts a object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     */
    EqualsBuilder<T> compareDeep(Function<? super T, ?> valueExtractor);

    /**
     * Configures an {@link Equals} instance to compare the references of values extracted by {@code valueExtractor} to determine the
     * equivalence between two instances of type {@code T}.
     * The value passed to {@code valueExtractor} is guaranteed to be non-null.
     *
     * @param valueExtractor Extracts a object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     */
    EqualsBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor);

    /**
     * Configures an {@link Equals} instance to use a custom {@code condition} to determine the equivalence between two
     * instances of type {@code T}. The values passed to {@code condition} is guaranteed to be non-null.
     *
     * @param condition condition that must be satisfied in order to satisfy equivalence.
     * @return {@code this}
     */
    EqualsBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition);

    /**
     * Builds an {@link Equals} instance.
     *
     * @return An instance of {@link Equals} that uses the configured {@code valueExtractor}s to determine the equivalence of
     * two instances of type {@code T}.
     */
    Equals<T> build();

}
