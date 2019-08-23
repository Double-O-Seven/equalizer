package ch.leadrian.equalizer;

import java.util.function.Predicate;

final class BooleanComparisonStep<T> implements ComparisonStep<T> {

    private final Predicate<? super T> valueExtractor;

    BooleanComparisonStep(Predicate<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        return valueExtractor.test(object1) == valueExtractor.test(object2);
    }
}
