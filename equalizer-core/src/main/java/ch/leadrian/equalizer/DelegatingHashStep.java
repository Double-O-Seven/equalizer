package ch.leadrian.equalizer;

import java.util.function.ToIntFunction;

final class DelegatingHashStep<T> implements HashStep<T> {

    private final ToIntFunction<? super T> delegate;

    DelegatingHashStep(ToIntFunction<? super T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public int hash(T object) {
        return delegate.applyAsInt(object);
    }
}
