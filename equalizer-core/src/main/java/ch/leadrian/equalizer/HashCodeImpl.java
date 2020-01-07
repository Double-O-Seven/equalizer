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
        public HashCodeBuilder<T> hashPrimitive(ToByteFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new ByteHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hashPrimitive(ToShortFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new ShortHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hashPrimitive(ToCharFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new CharHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hashPrimitive(ToIntFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new IntHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hashPrimitive(ToLongFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new LongHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hashPrimitive(ToFloatFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new FloatHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hashPrimitive(ToDoubleFunction<? super T> valueExtractor) {
            requireNonNull(valueExtractor, "valueExtractor must not be null");
            return addHashStep(new DoubleHashStep<>(valueExtractor));
        }

        @Override
        public HashCodeBuilder<T> hashPrimitive(Predicate<? super T> valueExtractor) {
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
