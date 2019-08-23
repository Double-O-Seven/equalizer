package ch.leadrian.equalizer;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public interface HashCodeBuilder<T> {

    HashCodeBuilder<T> withSuper(HashCode<? super T> superHashCode);

    HashCodeBuilder<T> hash(Function<? super T, ?> valueExtractor);

    HashCodeBuilder<T> hash(ToIntFunction<? super T> valueExtractor);

    HashCodeBuilder<T> hash(ToLongFunction<? super T> valueExtractor);

    HashCodeBuilder<T> hash(ToDoubleFunction<? super T> valueExtractor);

    HashCodeBuilder<T> hash(Predicate<? super T> valueExtractor);

    HashCodeBuilder<T> hashDeep(Function<? super T, ?> valueExtractor);

    HashCodeBuilder<T> hashIdentity(Function<? super T, ?> valueExtractor);

    HashCode<T> build();

}
