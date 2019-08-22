package ch.leadrian.equalizer;

import java.util.function.BiPredicate;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class EquivalenceAndHashImpl<T> implements EquivalenceAndHash<T> {

    private final Equivalence<T> equivalence;
    private final Hash<T> hash;

    private EquivalenceAndHashImpl(Equivalence<T> equivalence, Hash<T> hash) {
        this.equivalence = equivalence;
        this.hash = hash;
    }

    @Override
    public boolean equals(T object, Object otherObject) {
        return equivalence.equals(object, otherObject);
    }

    @Override
    public int hashCode(T object) {
        return hash.hashCode(object);
    }

    static final class Builder<T> implements EquivalenceAndHashBuilder<T> {

        private final EquivalenceBuilder<T> equivalenceBuilder;
        private final HashBuilder<T> hashBuilder;

        Builder(Class<T> targetClass) {
            requireNonNull(targetClass, "targetClass must not be null");
            this.equivalenceBuilder = Equalizer.equalsBuilder(targetClass);
            this.hashBuilder = Equalizer.hashCodeBuilder();
        }

        @Override
        public EquivalenceAndHashBuilder<T> withSuper(EquivalenceAndHash<? super T> superEquivalenceAndHash) {
            equivalenceBuilder.withSuper(superEquivalenceAndHash);
            hashBuilder.withSuper(superEquivalenceAndHash);
            return this;
        }

        @Override
        public EquivalenceAndHashBuilder<T> compare(Function<? super T, ?> valueExtractor) {
            equivalenceBuilder.compare(valueExtractor);
            return this;
        }

        @Override
        public EquivalenceAndHashBuilder<T> compareDeep(Function<? super T, ?> valueExtractor) {
            equivalenceBuilder.compareDeep(valueExtractor);
            return this;
        }

        @Override
        public EquivalenceAndHashBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor) {
            equivalenceBuilder.compareIdentity(valueExtractor);
            return this;
        }

        @Override
        public EquivalenceAndHashBuilder<T> compareAndHash(Function<? super T, ?> valueExtractor) {
            equivalenceBuilder.compare(valueExtractor);
            hashBuilder.hash(valueExtractor);
            return this;
        }

        @Override
        public EquivalenceAndHashBuilder<T> compareAndHashDeep(Function<? super T, ?> valueExtractor) {
            equivalenceBuilder.compareDeep(valueExtractor);
            hashBuilder.hashDeep(valueExtractor);
            return this;
        }

        @Override
        public EquivalenceAndHashBuilder<T> compareAndHashIdentity(Function<? super T, ?> valueExtractor) {
            equivalenceBuilder.compareIdentity(valueExtractor);
            hashBuilder.hashIdentity(valueExtractor);
            return this;
        }

        @Override
        public EquivalenceAndHashBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition) {
            equivalenceBuilder.equalIf(condition);
            return this;
        }

        @Override
        public EquivalenceAndHash<T> build() {
            return new EquivalenceAndHashImpl<>(equivalenceBuilder.build(), hashBuilder.build());
        }
    }
}
