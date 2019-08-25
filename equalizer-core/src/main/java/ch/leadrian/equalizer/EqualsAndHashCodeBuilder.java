package ch.leadrian.equalizer;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public interface EqualsAndHashCodeBuilder<T> {

    EqualsAndHashCodeBuilder<T> withSuper(EqualsAndHashCode<? super T> superEqualsAndHashCode);

    EqualsAndHashCodeBuilder<T> compare(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> comparePrimitive(ToIntFunction<? super T> valueExtractor);

    EqualsAndHashCodeBuilder<T> comparePrimitive(ToLongFunction<? super T> valueExtractor);

    EqualsAndHashCodeBuilder<T> comparePrimitive(ToDoubleFunction<? super T> valueExtractor);

    EqualsAndHashCodeBuilder<T> comparePrimitive(Predicate<? super T> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareDeep(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareAndHash(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToIntFunction<? super T> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToLongFunction<? super T> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToDoubleFunction<? super T> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(Predicate<? super T> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareAndHashDeep(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareAndHashIdentity(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition);

    EqualsAndHashCode<T> build();

}
