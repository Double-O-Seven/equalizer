package ch.leadrian.equalizer;

import java.util.function.ToDoubleFunction;

final class DoubleHashStep<T> implements HashStep<T> {

    private final ToDoubleFunction<? super T> valueExtractor;

    DoubleHashStep(ToDoubleFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public int hash(T object) {
        return Double.hashCode(valueExtractor.applyAsDouble(object));
    }
}
