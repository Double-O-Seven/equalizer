package ch.leadrian.equalizer;

import java.util.function.Predicate;

final class BooleanHashStep<T> implements HashStep<T> {

    private final Predicate<? super T> valueExtractor;

    BooleanHashStep(Predicate<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public int hash(T object) {
        return Boolean.hashCode(valueExtractor.test(object));
    }
}
