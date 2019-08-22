package ch.leadrian.equalizer;

import java.util.function.BiPredicate;
import java.util.function.Function;

public interface HashBuilder<T> {

    EquivalenceBuilder<T> superHash(Hash<? super T> superHash);

    EquivalenceBuilder<T> hash(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> hashDeep(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> hashIdentity(Function<? super T, ?> valueExtractor);

    EquivalenceBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition);

    Hash<T> build();

}
