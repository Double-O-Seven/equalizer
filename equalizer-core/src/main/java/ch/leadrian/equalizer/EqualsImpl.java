package ch.leadrian.equalizer;

import ch.leadrian.equalizer.util.function.ToByteFunction;
import ch.leadrian.equalizer.util.function.ToCharFunction;
import ch.leadrian.equalizer.util.function.ToFloatFunction;
import ch.leadrian.equalizer.util.function.ToShortFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import static java.util.Objects.requireNonNull;

final class EqualsImpl<T> implements Equals<T> {

    private final Class<T> targetClass;
    private final List<ComparisonStep<T>> comparisonSteps;

    private EqualsImpl(Class<T> targetClass, List<? extends ComparisonStep<T>> comparisonSteps) {
        this.targetClass = targetClass;
        this.comparisonSteps = new ArrayList<>(comparisonSteps);
    }

    @Override
    public boolean equals(T object, Object otherObject) {
        if (object == otherObject) {
            return true;
        }

        if (object == null || otherObject == null) {
            return false;
        }

        if (!targetClass.isInstance(otherObject)) {
            return false;
        }

        return isEqual(object, targetClass.cast(otherObject));
    }

    private boolean isEqual(T object, T otherObject) {
        if (comparisonSteps.isEmpty()) {
            return false;
        }

        // Avoid any garbage
        for (int i = 0; i < comparisonSteps.size(); i++) {
            if (!comparisonSteps.get(i).isEqual(object, otherObject)) {
                return false;
            }
        }
        return true;
    }

    static final class Builder<T> implements EqualsBuilder<T> {

        private final Class<T> targetClass;
        private final List<ComparisonStep<T>> comparisonSteps = new ArrayList<>();

        Builder(Class<T> targetClass) {
            requireNonNull(targetClass, "targetClass must not be null");
            this.targetClass = targetClass;
        }

        @Override
        public EqualsBuilder<T> withSuper(Equals<? super T> superEquals) {
            requireNonNull(superEquals, "superEquals must not be null");
            return equalIf(superEquals::equals);
        }

        @Override
        public EqualsBuilder<T> compare(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new ShallowComparisonStep<>(valueExtractor));
        }

        @Override
        public EqualsBuilder<T> comparePrimitive(ToByteFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new ByteComparisonStep<>(valueExtractor));
        }

        @Override
        public EqualsBuilder<T> comparePrimitive(ToShortFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new ShortComparisonStep<>(valueExtractor));
        }

        @Override
        public EqualsBuilder<T> comparePrimitive(ToCharFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new CharComparisonStep<>(valueExtractor));
        }

        @Override
        public EqualsBuilder<T> comparePrimitive(ToIntFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new IntComparisonStep<>(valueExtractor));
        }

        @Override
        public EqualsBuilder<T> comparePrimitive(ToLongFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new LongComparisonStep<>(valueExtractor));
        }

        @Override
        public EqualsBuilder<T> comparePrimitive(ToFloatFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new FloatComparisonStep<>(valueExtractor));
        }

        @Override
        public EqualsBuilder<T> comparePrimitive(ToDoubleFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new DoubleComparisonStep<>(valueExtractor));
        }

        @Override
        public EqualsBuilder<T> comparePrimitive(Predicate<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new BooleanComparisonStep<>(valueExtractor));
        }

        @Override
        public EqualsBuilder<T> compareDeep(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new DeepComparisonStep<>(valueExtractor));
        }

        @Override
        public EqualsBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new IdentityComparisonStep<>(valueExtractor));
        }

        @Override
        public EqualsBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition) {
            requireNonNull(condition, "condition must not be null");
            return addComparisonStep(new DelegatingComparisonStep<>(condition));
        }

        private EqualsBuilder<T> addComparisonStep(ComparisonStep<T> step) {
            comparisonSteps.add(step);
            return this;
        }

        @Override
        public Equals<T> build() {
            return new EqualsImpl<>(targetClass, comparisonSteps);
        }
    }
}
