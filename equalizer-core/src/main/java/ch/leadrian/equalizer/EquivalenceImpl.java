package ch.leadrian.equalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class EquivalenceImpl<T> implements Equivalence<T> {

    private final Class<T> targetClass;
    private final List<ComparisonStep<T>> comparisonSteps;

    private EquivalenceImpl(Class<T> targetClass, List<? extends ComparisonStep<T>> comparisonSteps) {
        this.targetClass = targetClass;
        this.comparisonSteps = new ArrayList<>(comparisonSteps);
    }

    @Override
    public boolean equals(T object, Object otherObject) {
        if (object == otherObject) {
            return true;
        }

        if (object == null) {
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

    static final class Builder<T> implements EquivalenceBuilder<T> {

        private final Class<T> targetClass;
        private final List<ComparisonStep<T>> comparisonSteps = new ArrayList<>();

        Builder(Class<T> targetClass) {
            requireNonNull(targetClass, "targetClass must not be null");
            this.targetClass = targetClass;
        }

        @Override
        public EquivalenceBuilder<T> withSuper(Equivalence<? super T> superEquivalence) {
            requireNonNull(superEquivalence, "superEquivalence must not be null");
            return equalIf(superEquivalence::equals);
        }

        @Override
        public EquivalenceBuilder<T> compare(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new ShallowComparisonStep<>(valueExtractor));
        }

        @Override
        public EquivalenceBuilder<T> compareDeep(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new DeepComparisonStep<>(valueExtractor));
        }

        @Override
        public EquivalenceBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addComparisonStep(new IdentityComparisonStep<>(valueExtractor));
        }

        @Override
        public EquivalenceBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition) {
            requireNonNull(condition, "condition must not be null");
            return addComparisonStep(new DelegatingComparisonStep<>(condition));
        }

        private EquivalenceBuilder<T> addComparisonStep(ComparisonStep<T> step) {
            comparisonSteps.add(step);
            return this;
        }

        @Override
        public Equivalence<T> build() {
            return new EquivalenceImpl<>(targetClass, comparisonSteps);
        }
    }
}
