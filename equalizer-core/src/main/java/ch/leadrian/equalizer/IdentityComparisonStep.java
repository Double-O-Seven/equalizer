package ch.leadrian.equalizer;

import java.util.function.Function;

final class IdentityComparisonStep<T> extends ValueExtractingComparisonStep<T> {

    IdentityComparisonStep(Function<? super T, ?> valueExtractor) {
        super(valueExtractor);
    }

    @Override
    boolean isValueEqual(Object value1, Object value2) {
        return value1 == value2;
    }
}
