/*
 * Copyright (C) 2020 Adrian-Philipp Leuenberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    private final ClassMatcher<T> classMatcher;

    private EqualsImpl(Class<T> targetClass, List<? extends ComparisonStep<T>> comparisonSteps, ClassMatcher<T> classMatcher) {
        this.targetClass = targetClass;
        this.comparisonSteps = new ArrayList<>(comparisonSteps);
        this.classMatcher = classMatcher;
    }

    @Override
    public boolean equals(T object, Object otherObject) {
        if (object == otherObject) {
            return true;
        }

        if (object == null || otherObject == null) {
            return false;
        }

        if (!classMatcher.classesMatch(object, otherObject)) {
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
        private ClassMatcher<T> classMatcher;

        Builder(Class<T> targetClass) {
            requireNonNull(targetClass, "targetClass must not be null");
            this.targetClass = targetClass;
            this.classMatcher = ClassMatchers.instanceOf(targetClass);
        }

        @Override
        public EqualsBuilder<T> withSuper(Equals<? super T> superEquals) {
            requireNonNull(superEquals, "superEquals must not be null");
            return equalIf(superEquals::equals);
        }

        @Override
        public EqualsBuilder<T> classMatcher(ClassMatcher<T> classMatcher) {
            requireNonNull(classMatcher, "classMatcher must not be null");
            this.classMatcher = classMatcher;
            return this;
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
            return new EqualsImpl<>(targetClass, comparisonSteps, classMatcher);
        }
    }
}
