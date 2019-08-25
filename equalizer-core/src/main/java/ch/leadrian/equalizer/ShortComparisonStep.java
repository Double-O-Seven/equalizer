package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToShortFunction;

final class ShortComparisonStep<T> implements ComparisonStep<T> {

    private final ToShortFunction<? super T> valueExtractor;

    ShortComparisonStep(ToShortFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        return valueExtractor.applyAsShort(object1) == valueExtractor.applyAsShort(object2);
    }
}
