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

import java.util.function.Function;

abstract class ValueExtractingComparisonStep<T> implements ComparisonStep<T> {

    private final Function<? super T, ?> valueExtractor;

    ValueExtractingComparisonStep(Function<? super T, ?> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public final boolean isEqual(T object1, T object2) {
        Object value1 = valueExtractor.apply(object1);
        Object value2 = valueExtractor.apply(object2);
        return isValueEqual(value1, value2);
    }

    abstract boolean isValueEqual(Object value1, Object value2);
}
