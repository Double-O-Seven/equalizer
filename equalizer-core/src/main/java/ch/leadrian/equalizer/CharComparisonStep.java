package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToCharFunction;

final class CharComparisonStep<T> implements ComparisonStep<T> {

    private final ToCharFunction<? super T> valueExtractor;

    CharComparisonStep(ToCharFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        return valueExtractor.applyAsChar(object1) == valueExtractor.applyAsChar(object2);
    }
}
