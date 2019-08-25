package ch.leadrian.equalizer;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public interface EqualsBuilder<T> {

    EqualsBuilder<T> withSuper(Equals<? super T> superEquals);

    EqualsBuilder<T> compare(Function<? super T, ?> valueExtractor);

    EqualsBuilder<T> comparePrimitive(ToIntFunction<? super T> valueExtractor);

    EqualsBuilder<T> comparePrimitive(ToLongFunction<? super T> valueExtractor);

    EqualsBuilder<T> comparePrimitive(ToDoubleFunction<? super T> valueExtractor);

    EqualsBuilder<T> comparePrimitive(Predicate<? super T> valueExtractor);

    EqualsBuilder<T> compareDeep(Function<? super T, ?> valueExtractor);

    EqualsBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor);

    EqualsBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition);

    Equals<T> build();

}
