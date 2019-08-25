package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToCharFunction;

final class CharHashStep<T> implements HashStep<T> {

    private final ToCharFunction<? super T> valueExtractor;

    CharHashStep(ToCharFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public int hash(T object) {
        return Character.hashCode(valueExtractor.applyAsChar(object));
    }
}
