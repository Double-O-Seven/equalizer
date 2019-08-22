package ch.leadrian.equalizer;

import java.util.function.BiPredicate;
import java.util.function.Function;

public interface EquivalenceBuilder<T> {

    EquivalenceBuilder<T> withSuper(Equivalence<? super T> superEquivalence);

    EquivalenceBuilder<T> compare(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> compareDeep(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition);

    Equivalence<T> build();

}
