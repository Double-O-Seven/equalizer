package ch.leadrian.equalizer;

import java.util.function.ToDoubleFunction;

import static java.lang.Double.doubleToLongBits;

final class DoubleComparisonStep<T> implements ComparisonStep<T> {

    private final ToDoubleFunction<? super T> valueExtractor;

    DoubleComparisonStep(ToDoubleFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        double value1 = valueExtractor.applyAsDouble(object1);
        double value2 = valueExtractor.applyAsDouble(object2);
        return doubleToLongBits(value1) == doubleToLongBits(value2);
    }
}
