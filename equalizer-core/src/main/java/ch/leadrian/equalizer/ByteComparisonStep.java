package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToByteFunction;

final class ByteComparisonStep<T> implements ComparisonStep<T> {

    private final ToByteFunction<? super T> valueExtractor;

    ByteComparisonStep(ToByteFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        return valueExtractor.applyAsByte(object1) == valueExtractor.applyAsByte(object2);
    }
}
