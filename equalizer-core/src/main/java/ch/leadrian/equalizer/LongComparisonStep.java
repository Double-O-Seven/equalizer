package ch.leadrian.equalizer;

import java.util.function.ToLongFunction;

final class LongComparisonStep<T> implements ComparisonStep<T> {

    private final ToLongFunction<? super T> valueExtractor;

    LongComparisonStep(ToLongFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        return valueExtractor.applyAsLong(object1) == valueExtractor.applyAsLong(object2);
    }
}
