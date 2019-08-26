package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToFloatFunction;

import static java.lang.Float.floatToIntBits;

final class FloatComparisonStep<T> implements ComparisonStep<T> {

    private final ToFloatFunction<? super T> valueExtractor;

    FloatComparisonStep(ToFloatFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        float value1 = valueExtractor.applyAsFloat(object1);
        float value2 = valueExtractor.applyAsFloat(object2);
        return floatToIntBits(value1) == floatToIntBits(value2);
    }
}
