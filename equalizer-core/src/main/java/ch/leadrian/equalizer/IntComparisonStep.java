package ch.leadrian.equalizer;

import java.util.function.ToIntFunction;

final class IntComparisonStep<T> implements ComparisonStep<T> {

    private final ToIntFunction<? super T> valueExtractor;

    IntComparisonStep(ToIntFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        return valueExtractor.applyAsInt(object1) == valueExtractor.applyAsInt(object2);
    }
}
