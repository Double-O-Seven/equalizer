/*
 * Copyright (C) 2019 Adrian-Philipp Leuenberger
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

package ch.leadrian.equalizer.util.function;

/**
 * Represents a function that produces a short-valued result.  This is the
 * {@code short}-producing primitive specialization for {@link java.util.function.Function},
 * which is missing in the JDK. To avoid any auto-boxing while comparing or hashing object attributes,
 * this function has been added to Equalizer for sake of completeness.
 *
 * @param <T> the type of the input to the function
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface ToShortFunction<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    short applyAsShort(T value);

}
