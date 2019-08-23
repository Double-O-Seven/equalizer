package ch.leadrian.equalizer;

import java.util.function.ToDoubleFunction;

final class DoubleComparisonStep<T> implements ComparisonStep<T> {

    private final ToDoubleFunction<? super T> valueExtractor;

    DoubleComparisonStep(ToDoubleFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        return valueExtractor.applyAsDouble(object1) == valueExtractor.applyAsDouble(object2);
    }
}
