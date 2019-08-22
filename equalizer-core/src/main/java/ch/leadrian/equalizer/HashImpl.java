package ch.leadrian.equalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class HashImpl<T> implements Hash<T> {

    private final List<HashStep<T>> hashSteps;

    private HashImpl(List<? extends HashStep<T>> hashSteps) {
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

    static final class Builder<T> implements HashBuilder<T> {

        private final List<HashStep<T>> hashSteps = new ArrayList<>();

        @Override
        public HashBuilder<T> withSuper(Hash<? super T> superHash) {
            requireNonNull(superHash, "superHash must not be null");
            return addHashStep(new DelegatingHashStep<>(superHash::hashCode));
        }

        @Override
        public HashBuilder<T> hash(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new ShallowHashStep<>(valueExtractor));
        }

        @Override
        public HashBuilder<T> hashDeep(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new DeepHashStep<>(valueExtractor));
        }

        @Override
        public HashBuilder<T> hashIdentity(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new IdentityHashStep<>(valueExtractor));
        }

        private HashBuilder<T> addHashStep(HashStep<T> step) {
            hashSteps.add(step);
            return this;
        }

        @Override
        public Hash<T> build() {
            return new HashImpl<>(hashSteps);
        }
    }
}
