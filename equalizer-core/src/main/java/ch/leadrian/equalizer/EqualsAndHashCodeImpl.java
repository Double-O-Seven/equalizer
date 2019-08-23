package ch.leadrian.equalizer;

import java.util.function.BiPredicate;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class EqualsAndHashCodeImpl<T> implements EqualsAndHashCode<T> {

    private final Equals<T> equals;
    private final HashCode<T> hashCode;

    private EqualsAndHashCodeImpl(Equals<T> equals, HashCode<T> hashCode) {
        this.equals = equals;
        this.hashCode = hashCode;
    }

    @Override
    public boolean equals(T object, Object otherObject) {
        return equals.equals(object, otherObject);
    }

    @Override
    public int hashCode(T object) {
        return hashCode.hashCode(object);
    }

    static final class Builder<T> implements EqualsAndHashCodeBuilder<T> {

        private final EqualsBuilder<T> equalsBuilder;
        private final HashCodeBuilder<T> hashCodeBuilder;

        Builder(Class<T> targetClass) {
            requireNonNull(targetClass, "targetClass must not be null");
            this.equalsBuilder = Equalizer.equalsBuilder(targetClass);
            this.hashCodeBuilder = Equalizer.hashCodeBuilder();
        }

        @Override
        public EqualsAndHashCodeBuilder<T> withSuper(EqualsAndHashCode<? super T> superEqualsAndHashCode) {
            equalsBuilder.withSuper(superEqualsAndHashCode);
            hashCodeBuilder.withSuper(superEqualsAndHashCode);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compare(Function<? super T, ?> valueExtractor) {
            equalsBuilder.compare(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareDeep(Function<? super T, ?> valueExtractor) {
            equalsBuilder.compareDeep(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor) {
            equalsBuilder.compareIdentity(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHash(Function<? super T, ?> valueExtractor) {
            equalsBuilder.compare(valueExtractor);
            hashCodeBuilder.hash(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashDeep(Function<? super T, ?> valueExtractor) {
            equalsBuilder.compareDeep(valueExtractor);
            hashCodeBuilder.hashDeep(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashIdentity(Function<? super T, ?> valueExtractor) {
            equalsBuilder.compareIdentity(valueExtractor);
            hashCodeBuilder.hashIdentity(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition) {
            equalsBuilder.equalIf(condition);
            return this;
        }

        @Override
        public EqualsAndHashCode<T> build() {
            return new EqualsAndHashCodeImpl<>(equalsBuilder.build(), hashCodeBuilder.build());
        }
    }
}
