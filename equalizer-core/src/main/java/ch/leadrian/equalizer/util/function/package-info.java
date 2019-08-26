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