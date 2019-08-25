package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToFloatFunction;

final class FloatHashStep<T> implements HashStep<T> {

    private final ToFloatFunction<? super T> valueExtractor;

    FloatHashStep(ToFloatFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public int hash(T object) {
        return Float.hashCode(valueExtractor.applyAsFloat(object));
    }
}
