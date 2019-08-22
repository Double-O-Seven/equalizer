package ch.leadrian.equalizer;

import java.util.Objects;
import java.util.function.Function;

final class DeepComparisonStep<T> extends ValueExtractingComparisonStep<T> {

    DeepComparisonStep(Function<? super T, ?> valueExtractor) {
        super(valueExtractor);
    }

    @Override
    boolean isValueEqual(Object value1, Object value2) {
        return Objects.deepEquals(value1, value2);
    }
}
