package ch.leadrian.equalizer;

import java.util.function.BiPredicate;
import java.util.function.Function;

public interface EquivalenceAndHashBuilder<T> {

    EquivalenceBuilder<T> compareAndHashSuper(EquivalenceAndHash<? super T> superEquivalenceAndHash);

    EquivalenceBuilder<T> compare(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> compareDeep(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> compareAndHash(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> compareAndHashDeep(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> compareAndHashIdentity(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition);

    EquivalenceAndHash<T> build();

}
