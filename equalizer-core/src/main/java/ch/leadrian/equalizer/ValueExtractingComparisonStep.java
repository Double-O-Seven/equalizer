package ch.leadrian.equalizer;

import java.util.function.Function;

abstract class ValueExtractingComparisonStep<T> implements ComparisonStep<T> {

    private final Function<? super T, ?> valueExtractor;

    ValueExtractingComparisonStep(Function<? super T, ?> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public final boolean isEqual(T object1, T object2) {
        Object value1 = valueExtractor.apply(object1);
        Object value2 = valueExtractor.apply(object2);
        return isValueEqual(value1, value2);
    }

    abstract boolean isValueEqual(Object value1, Object value2);
}
