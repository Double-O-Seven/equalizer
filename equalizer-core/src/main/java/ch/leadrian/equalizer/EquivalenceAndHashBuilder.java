package ch.leadrian.equalizer;

import java.util.function.BiPredicate;
import java.util.function.Function;

public interface EquivalenceAndHashBuilder<T> {

    EquivalenceAndHashBuilder<T> withSuper(EquivalenceAndHash<? super T> superEquivalenceAndHash);

    EquivalenceAndHashBuilder<T> compare(Function<? super T, ?> valueExtractor);

    EquivalenceAndHashBuilder<T> compareDeep(Function<? super T, ?> valueExtractor);

    EquivalenceAndHashBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor);

    EquivalenceAndHashBuilder<T> compareAndHash(Function<? super T, ?> valueExtractor);

    EquivalenceAndHashBuilder<T> compareAndHashDeep(Function<? super T, ?> valueExtractor);

    EquivalenceAndHashBuilder<T> compareAndHashIdentity(Function<? super T, ?> valueExtractor);

    EquivalenceAndHashBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition);

    EquivalenceAndHash<T> build();

}
