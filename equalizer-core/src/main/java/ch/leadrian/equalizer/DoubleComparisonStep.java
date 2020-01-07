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

import java.util.function.ToDoubleFunction;

import static java.lang.Double.doubleToLongBits;

final class DoubleComparisonStep<T> implements ComparisonStep<T> {

    private final ToDoubleFunction<? super T> valueExtractor;

    DoubleComparisonStep(ToDoubleFunction<? super T> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public boolean isEqual(T object1, T object2) {
        double value1 = valueExtractor.applyAsDouble(object1);
        double value2 = valueExtractor.applyAsDouble(object2);
        return doubleToLongBits(value1) == doubleToLongBits(value2);
    }
}
