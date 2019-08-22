package ch.leadrian.equalizer;

import java.util.function.BiPredicate;

final class DelegatingComparisonStep<T> implements ComparisonStep<T> {

    private final BiPredicate<? super T, ? super T> delegate;

    DelegatingComparisonStep(BiPredicate<? super T, ? super T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        return delegate.test(object1, object2);
    }
}
