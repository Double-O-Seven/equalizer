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

/**
 * Collection of object-to-primitive functions that are missing from the JDK. The functions have been added in order to
 * provide correct, garbage-free implementations of {@link java.lang.Object#equals(java.lang.Object)} and
 * {@link java.lang.Object#hashCode()}, including garbage produced by auto-boxing.
 *
 * @see java.util.function.ToIntFunction
 * @see java.util.function.ToLongFunction
 * @see java.util.function.ToDoubleFunction
 * @see java.util.function.Predicate
 */
package ch.leadrian.equalizer.util.function;