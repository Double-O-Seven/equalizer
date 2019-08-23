package ch.leadrian.equalizer;

import java.util.function.Function;

public interface HashCodeBuilder<T> {

    HashCodeBuilder<T> withSuper(HashCode<? super T> superHashCode);

    HashCodeBuilder<T> hash(Function<? super T, ?> valueExtractor);

    HashCodeBuilder<T> hashDeep(Function<? super T, ?> valueExtractor);

    HashCodeBuilder<T> hashIdentity(Function<? super T, ?> valueExtractor);

    HashCode<T> build();

}
