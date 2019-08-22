package ch.leadrian.equalizer;

import java.util.Objects;
import java.util.function.Function;

final class ShallowComparisonStep<T> extends ValueExtractingComparisonStep<T> {

    ShallowComparisonStep(Function<? super T, ?> valueExtractor) {
        super(valueExtractor);
    }

    @Override
    boolean isValueEqual(Object value1, Object value2) {
        return Objects.equals(value1, value2);
    }
}
