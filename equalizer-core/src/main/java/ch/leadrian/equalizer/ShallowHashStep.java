package ch.leadrian.equalizer;

import java.util.Objects;
import java.util.function.Function;

final class ShallowHashStep<T> extends ValueExtractingHashStep<T> {

    ShallowHashStep(Function<? super T, ?> valueExtractor) {
        super(valueExtractor);
    }

    @Override
    int hashValue(Object value) {
        return Objects.hashCode(value);
    }
}
