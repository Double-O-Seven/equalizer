package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToByteFunction;

final class ByteHashStep<T> implements HashStep<T> {

    private final ToByteFunction<? super T> valueExtractor;

    ByteHashStep(ToByteFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public int hash(T object) {
        return Byte.hashCode(valueExtractor.applyAsByte(object));
    }
}
