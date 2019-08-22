package ch.leadrian.equalizer;

import java.util.function.Function;

final class IdentityHashStep<T> extends ValueExtractingHashStep<T> {

    IdentityHashStep(Function<? super T, ?> valueExtractor) {
        super(valueExtractor);
    }

    @Override
    int hashValue(Object value) {
        return System.identityHashCode(value);
    }
}
