package ch.leadrian.equalizer;

import java.util.function.Function;

abstract class ValueExtractingHashStep<T> implements HashStep<T> {

    private final Function<? super T, ?> valueExtractor;

    ValueExtractingHashStep(Function<? super T, ?> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public final int hash(T object) {
        return hashValue(valueExtractor.apply(object));
    }

    abstract int hashValue(Object value);

}
