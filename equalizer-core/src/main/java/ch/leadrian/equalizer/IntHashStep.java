package ch.leadrian.equalizer;

import java.util.function.ToIntFunction;

final class IntHashStep<T> implements HashStep<T> {

    private final ToIntFunction<? super T> valueExtractor;

    IntHashStep(ToIntFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public int hash(T object) {
        return Integer.hashCode(valueExtractor.applyAsInt(object));
    }
}
