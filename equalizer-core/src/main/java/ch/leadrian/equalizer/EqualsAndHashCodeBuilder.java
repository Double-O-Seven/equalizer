package ch.leadrian.equalizer;

import java.util.function.BiPredicate;
import java.util.function.Function;

public interface EqualsAndHashCodeBuilder<T> {

    EqualsAndHashCodeBuilder<T> withSuper(EqualsAndHashCode<? super T> superEqualsAndHashCode);

    EqualsAndHashCodeBuilder<T> compare(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareDeep(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareAndHash(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareAndHashDeep(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> compareAndHashIdentity(Function<? super T, ?> valueExtractor);

    EqualsAndHashCodeBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition);

    EqualsAndHashCode<T> build();

}
