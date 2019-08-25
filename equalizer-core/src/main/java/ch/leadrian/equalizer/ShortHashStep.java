package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToShortFunction;

final class ShortHashStep<T> implements HashStep<T> {

    private final ToShortFunction<? super T> valueExtractor;

    ShortHashStep(ToShortFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public int hash(T object) {
        return Short.hashCode(valueExtractor.applyAsShort(object));
    }
}
