package ch.leadrian.equalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import static java.util.Objects.requireNonNull;

final class HashCodeImpl<T> implements HashCode<T> {

    private final List<HashStep<T>> hashSteps;

    private HashCodeImpl(List<? extends HashStep<T>> hashSteps) {
        this.hashSteps = new ArrayList<>(hashSteps);
    }

    @Override
    public int hashCode(T object) {
        if (object == null) {
            return 0;
        }

        if (hashSteps.isEmpty()) {
            return System.identityHashCode(object);
        }

        int result = 1;
        for (int i = 0; i < hashSteps.size(); i++) {
            result = 31 * result + hashSteps.get(i).hash(object);
        }
        return result;
    }

    static final class Builder<T> implements HashCodeBuilder<T> {

        private final List<HashStep<T>> hashSteps = new ArrayList<>();

        @Override
        public HashCodeBuilder<T> withSuper(HashCode<? super T> superHashCode) {
            requireNonNull(superHashCode, "superHashCode must not be null");
            return addHashStep(new DelegatingHashStep<>(superHashCode::hashCode));
        }

        @Override
        public HashCodeBuilder<T> hash(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new ShallowHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hash(ToIntFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new IntHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hash(ToLongFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new LongHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hash(ToDoubleFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new DoubleHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hash(Predicate<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new BooleanHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hashDeep(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new DeepHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hashIdentity(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new IdentityHashStep<>(valueExtractor));
        }

        private HashCodeBuilder<T> addHashStep(HashStep<T> step) {
            hashSteps.add(step);
            return this;
        }

        @Override
        public HashCode<T> build() {
            return new HashCodeImpl<>(hashSteps);
        }
    }
}
