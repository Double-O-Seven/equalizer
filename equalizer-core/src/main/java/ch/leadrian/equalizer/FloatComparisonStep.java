package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToFloatFunction;

final class FloatComparisonStep<T> implements ComparisonStep<T> {

    private final ToFloatFunction<? super T> valueExtractor;

    FloatComparisonStep(ToFloatFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        return valueExtractor.applyAsFloat(object1) == valueExtractor.applyAsFloat(object2);
    }
}
