package ch.leadrian.equalizer;

import java.util.function.BiPredicate;
import java.util.function.Function;

public interface HashBuilder<T> {

    HashBuilder<T> withSuper(Hash<? super T> superHash);

    HashBuilder<T> hash(Function<? super T, ?> valueExtractor);

    HashBuilder<T> hashDeep(Function<? super T, ?> valueExtractor);

    HashBuilder<T> hashIdentity(Function<? super T, ?> valueExtractor);

    HashBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition);

    Hash<T> build();

}
