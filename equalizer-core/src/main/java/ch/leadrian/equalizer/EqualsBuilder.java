package ch.leadrian.equalizer;

import java.util.function.BiPredicate;
import java.util.function.Function;

public interface EqualsBuilder<T> {

    EqualsBuilder<T> withSuper(Equals<? super T> superEquals);

    EqualsBuilder<T> compare(Function<? super T, ?> valueExtractor);

    EqualsBuilder<T> compareDeep(Function<? super T, ?> valueExtractor);

    EqualsBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor);

    EqualsBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition);

    Equals<T> build();

}
