package ch.leadrian.equalizer;

import java.util.function.ToLongFunction;

final class LongHashStep<T> implements HashStep<T> {

    private final ToLongFunction<? super T> valueExtractor;

    LongHashStep(ToLongFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public int hash(T object) {
        return Long.hashCode(valueExtractor.applyAsLong(object));
    }
}
