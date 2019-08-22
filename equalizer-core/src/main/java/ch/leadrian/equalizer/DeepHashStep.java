package ch.leadrian.equalizer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

final class DeepHashStep<T> extends ValueExtractingHashStep<T> {

    DeepHashStep(Function<? super T, ?> valueExtractor) {
        super(valueExtractor);
    }

    @Override
    int hashValue(Object value) {
        if (value instanceof Object[]) {
            return Arrays.deepHashCode((Object[]) value);
        } else if (value instanceof byte[]) {
            return Arrays.hashCode((byte[]) value);
        } else if (value instanceof short[]) {
            return Arrays.hashCode((short[]) value);
        } else if (value instanceof int[]) {
            return Arrays.hashCode((int[]) value);
        } else if (value instanceof long[]) {
            return Arrays.hashCode((long[]) value);
        } else if (value instanceof char[]) {
            return Arrays.hashCode((char[]) value);
        } else if (value instanceof float[]) {
            return Arrays.hashCode((float[]) value);
        } else if (value instanceof double[]) {
            return Arrays.hashCode((double[]) value);
        } else if (value instanceof boolean[]) {
            return Arrays.hashCode((boolean[]) value);
        } else {
            return Objects.hashCode(value);
        }
    }
}
