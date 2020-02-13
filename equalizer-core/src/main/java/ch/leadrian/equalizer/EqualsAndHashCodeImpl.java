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

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

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
            requireNonNull(superEqualsAndHashCode, "superEqualsAndHashCode must be null");
            equalsBuilder.withSuper(superEqualsAndHashCode);
            hashCodeBuilder.withSuper(superEqualsAndHashCode);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> classMatcher(ClassMatcher<T> classMatcher) {
            equalsBuilder.classMatcher(classMatcher);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compare(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.compare(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> comparePrimitive(ToByteFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> comparePrimitive(ToShortFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> comparePrimitive(ToCharFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> comparePrimitive(ToIntFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> comparePrimitive(ToLongFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> comparePrimitive(ToFloatFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> comparePrimitive(ToDoubleFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> comparePrimitive(Predicate<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareDeep(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.compareDeep(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareIdentity(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.compareIdentity(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHash(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.compare(valueExtractor);
            hashCodeBuilder.hash(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToByteFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            hashCodeBuilder.hashPrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToShortFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            hashCodeBuilder.hashPrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToCharFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            hashCodeBuilder.hashPrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToIntFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            hashCodeBuilder.hashPrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToLongFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            hashCodeBuilder.hashPrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToFloatFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            hashCodeBuilder.hashPrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(ToDoubleFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            hashCodeBuilder.hashPrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashPrimitive(Predicate<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.comparePrimitive(valueExtractor);
            hashCodeBuilder.hashPrimitive(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashDeep(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.compareDeep(valueExtractor);
            hashCodeBuilder.hashDeep(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> compareAndHashIdentity(Function<? super T, ?> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must be null");
            equalsBuilder.compareIdentity(valueExtractor);
            hashCodeBuilder.hashIdentity(valueExtractor);
            return this;
        }

        @Override
        public EqualsAndHashCodeBuilder<T> equalIf(BiPredicate<? super T, ? super T> condition) {
            requireNonNull(condition, "condition must be null");
            equalsBuilder.equalIf(condition);
            return this;
        }

        @Override
        public EqualsAndHashCode<T> build() {
            validate();
            return new EqualsAndHashCodeImpl<>(equalsBuilder.build(), hashCodeBuilder.build());
        }

        private void validate() {
            if (!equalsBuilder.isEmpty() && hashCodeBuilder.isEmpty()) {
                throw new IllegalStateException("If at least one comparison step has configured, then at least one hash step must be configured as well");
            }
        }

        @Override
        public boolean isEmpty() {
            return equalsBuilder.isEmpty() && hashCodeBuilder.isEmpty();
        }

    }

}
