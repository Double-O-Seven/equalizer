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
 * A builder for {@link EqualsAndHashCode}. It is used to configure which attributes of type {@link T} are used to
 * determine equality between two instances of type {@code T} as well as the attributes used to compute the hash code
 * of an instance of type {@code T}.
 * Use {@link EqualsAndHashCode} to guarantee that the attributes used to compute the hash code are a subset of the
 * attributes used to determine equivalence of two instances of type {@code T}.
 *
 * @param <T> type for which an {@link EqualsAndHashCode} instance should be built
 * @see Equals
 * @see HashCode
 * @see EqualsAndHashCode
 */
public interface EqualsAndHashCodeBuilder<T> {

    /**
     * Applies {@code superEqualsAndHashCode} when computing equivalence and hash code.
     *
     * @param superEqualsAndHashCode {@link Equals} instance used to compute the equivalence and hash code for a supertype of {@code T}.
     * @return {@code this}
     * @see EqualsBuilder#withSuper(Equals)
     * @see HashCodeBuilder#withSuper(HashCode)
     */
    EqualsAndHashCodeBuilder<T> withSuper(EqualsAndHashCode<? super T> superEqualsAndHashCode);

    /**
     * Configures a {@link ClassMatcher} for the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param classMatcher class matcher used to determine whether two instances of {@code T} can be compared
     * @return {@code this}
     * @see ClassMatcher
     * @see ClassMatchers
     * @see EqualsBuilder#classMatcher(ClassMatcher)
     */
    EqualsAndHashCodeBuilder<T> classMatcher(ClassMatcher<T> classMatcher);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#compare(Function)
     */
    EqualsAndHashCodeBuilder<T> compare(Function<? super T, ?> valueExtractor);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#compare(Function)
     */
    EqualsAndHashCodeBuilder<T> comparePrimitive(ToByteFunction<? super T> valueExtractor);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToShortFunction)
     */
    EqualsAndHashCodeBuilder<T> comparePrimitive(ToShortFunction<? super T> valueExtractor);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToCharFunction)
     */
    EqualsAndHashCodeBuilder<T> comparePrimitive(ToCharFunction<? super T> valueExtractor);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToIntFunction)
     */
    EqualsAndHashCodeBuilder<T> comparePrimitive(ToIntFunction<? super T> valueExtractor);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToLongFunction)
     */
    EqualsAndHashCodeBuilder<T> comparePrimitive(ToLongFunction<? super T> valueExtractor);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToFloatFunction)
     */
    EqualsAndHashCodeBuilder<T> comparePrimitive(ToFloatFunction<? super T> valueExtractor);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToDoubleFunction)
     */
    EqualsAndHashCodeBuilder<T> comparePrimitive(ToDoubleFunction<? super T> valueExtractor);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(Predicate)
     */
    EqualsAndHashCodeBuilder<T> comparePrimitive(Predicate<? super T> valueExtractor);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}.
     * @return {@code this}
     * @see EqualsBuilder#compareDeep(Function)
     */
    EqualsAndHashCodeBuilder<T> compareDeep(Function<? super T, ?> valueExtractor);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}.
     * @return {@code this}
     * @see EqualsBuilder#compareIdentity(Function)
     */
    EqualsAndHashCodeBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor);

    /**
     * Configure both the {@link Equals} part and {@link HashCode} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#compare(Function)
     * @see HashCodeBuilder#hash(Function)
     */
    EqualsAndHashCodeBuilder<T> compareAndHash(Function<? super T, ?> valueExtractor);

    /**
     * Configure both the {@link Equals} part and {@link HashCode} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToByteFunction)
     * @see HashCodeBuilder#hashPrimitive(ToByteFunction)
     */
    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToByteFunction<? super T> valueExtractor);

    /**
     * Configure both the {@link Equals} part and {@link HashCode} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToShortFunction)
     * @see HashCodeBuilder#hashPrimitive(ToShortFunction)
     */
    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToShortFunction<? super T> valueExtractor);

    /**
     * Configure both the {@link Equals} part and {@link HashCode} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToCharFunction)
     * @see HashCodeBuilder#hashPrimitive(ToCharFunction)
     */
    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToCharFunction<? super T> valueExtractor);

    /**
     * Configure both the {@link Equals} part and {@link HashCode} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToIntFunction)
     * @see HashCodeBuilder#hashPrimitive(ToIntFunction)
     */
    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToIntFunction<? super T> valueExtractor);

    /**
     * Configure both the {@link Equals} part and {@link HashCode} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToLongFunction)
     * @see HashCodeBuilder#hashPrimitive(ToLongFunction)
     */
    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToLongFunction<? super T> valueExtractor);

    /**
     * Configure both the {@link Equals} part and {@link HashCode} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToFloatFunction)
     * @see HashCodeBuilder#hashPrimitive(ToFloatFunction)
     */
    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToFloatFunction<? super T> valueExtractor);

    /**
     * Configure both the {@link Equals} part and {@link HashCode} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(ToDoubleFunction)
     * @see HashCodeBuilder#hashPrimitive(ToDoubleFunction)
     */
    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToDoubleFunction<? super T> valueExtractor);

    /**
     * Configure both the {@link Equals} part and {@link HashCode} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#comparePrimitive(Predicate)
     * @see HashCodeBuilder#hashPrimitive(Predicate)
     */
    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(Predicate<? super T> valueExtractor);

    /**
     * Configure both the {@link Equals} part and {@link HashCode} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#compareDeep(Function)
     * @see HashCodeBuilder#hashDeep(Function)
     */
    EqualsAndHashCodeBuilder<T> compareAndHashDeep(Function<? super T, ?> valueExtractor);

    /**
     * Configure both the {@link Equals} part and {@link HashCode} part of {@link EqualsAndHashCode}.
     *
     * @param valueExtractor Extracts an object value of an instance of type {@code T}. The extracted value may be {@code null}.
     * @return {@code this}
     * @see EqualsBuilder#compareIdentity(Function)
     * @see HashCodeBuilder#hashIdentity(Function)
     */
    EqualsAndHashCodeBuilder<T> compareAndHashIdentity(Function<? super T, ?> valueExtractor);

    /**
     * Configure the {@link Equals} part of {@link EqualsAndHashCode}.
     *
     * @param condition condition that must be satisfied in order to satisfy equivalence.
     * @return {@code this}
     * @see EqualsBuilder#equalIf(BiPredicate)
     */
    EqualsAndHashCodeBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition);

    /**
     * Builds an {@link EqualsAndHashCode} instance.
     *
     * @return An instance of {@link EqualsAndHashCode} that uses the configured {@code valueExtractors} to determine
     * the equivalence of two instances of type {@code T} and compute the hash code of an instance of type {@code T}.
     */
    EqualsAndHashCode<T> build();

}
