package ch.leadrian.equalizer

import ch.leadrian.equalizer.util.function.ToByteFunction
import ch.leadrian.equalizer.util.function.ToCharFunction
import ch.leadrian.equalizer.util.function.ToFloatFunction
import ch.leadrian.equalizer.util.function.ToShortFunction
import java.util.function.Predicate
import java.util.function.ToDoubleFunction
import java.util.function.ToIntFunction
import java.util.function.ToLongFunction

inline fun <reified T : Any> equals(block: EqualsBuilder<T>.() -> Unit): Equals<T> {
    return Equalizer.equalsBuilder(T::class.java).apply(block).build()
}

inline fun <T : Any> hashCode(block: HashCodeBuilder<T>.() -> Unit): HashCode<T> {
    return Equalizer.hashCodeBuilder<T>().apply(block).build()
}

inline fun <reified T : Any> equalsAndHashCode(block: EqualsAndHashCodeBuilder<T>.() -> Unit): EqualsAndHashCode<T> {
    return Equalizer.equalsAndHashCodeBuilder(T::class.java).apply(block).build()
}

inline fun <T : Any> EqualsBuilder<T>.compare(crossinline valueExtractor: T.() -> Any?): EqualsBuilder<T> {
    return compare { valueExtractor(it) }
}

inline fun <T : Any> HashCodeBuilder<T>.hash(crossinline valueExtractor: T.() -> Any?): HashCodeBuilder<T> {
    return hash { valueExtractor(it) }
}

inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compare(crossinline valueExtractor: T.() -> Any?): EqualsAndHashCodeBuilder<T> {
    return compare { valueExtractor(it) }
}

inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compareAndHash(crossinline valueExtractor: T.() -> Any?): EqualsAndHashCodeBuilder<T> {
    return compareAndHash { valueExtractor(it) }
}

inline fun <T : Any> EqualsBuilder<T>.compareDeep(crossinline valueExtractor: T.() -> Any?): EqualsBuilder<T> {
    return compareDeep { valueExtractor(it) }
}

inline fun <T : Any> HashCodeBuilder<T>.hashDeep(crossinline valueExtractor: T.() -> Any?): HashCodeBuilder<T> {
    return hashDeep { valueExtractor(it) }
}

inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compareDeep(crossinline valueExtractor: T.() -> Any?): EqualsAndHashCodeBuilder<T> {
    return compareDeep { valueExtractor(it) }
}

inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compareAndHashDeep(crossinline valueExtractor: T.() -> Any?): EqualsAndHashCodeBuilder<T> {
    return compareAndHashDeep { valueExtractor(it) }
}

@JvmName("compareByte")
inline fun <T : Any> EqualsBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Byte): EqualsBuilder<T> {
    return comparePrimitive(ToByteFunction { valueExtractor(it) })
}

@JvmName("hashByte")
inline fun <T : Any> HashCodeBuilder<T>.hashPrimitive(crossinline valueExtractor: T.() -> Byte): HashCodeBuilder<T> {
    return hashPrimitive(ToByteFunction { valueExtractor(it) })
}

@JvmName("compareByte")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Byte): EqualsAndHashCodeBuilder<T> {
    return comparePrimitive(ToByteFunction { valueExtractor(it) })
}

@JvmName("compareAndHashByte")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compareAndHashPrimitive(crossinline valueExtractor: T.() -> Byte): EqualsAndHashCodeBuilder<T> {
    return compareAndHashPrimitive(ToByteFunction { valueExtractor(it) })
}

@JvmName("compareShort")
inline fun <T : Any> EqualsBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Short): EqualsBuilder<T> {
    return comparePrimitive(ToShortFunction { valueExtractor(it) })
}

@JvmName("hashShort")
inline fun <T : Any> HashCodeBuilder<T>.hashPrimitive(crossinline valueExtractor: T.() -> Short): HashCodeBuilder<T> {
    return hashPrimitive(ToShortFunction { valueExtractor(it) })
}

@JvmName("compareShort")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Short): EqualsAndHashCodeBuilder<T> {
    return comparePrimitive(ToShortFunction { valueExtractor(it) })
}

@JvmName("compareAndHashShort")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compareAndHashPrimitive(crossinline valueExtractor: T.() -> Short): EqualsAndHashCodeBuilder<T> {
    return compareAndHashPrimitive(ToShortFunction { valueExtractor(it) })
}

@JvmName("compareChar")
inline fun <T : Any> EqualsBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Char): EqualsBuilder<T> {
    return comparePrimitive(ToCharFunction { valueExtractor(it) })
}

@JvmName("hashChar")
inline fun <T : Any> HashCodeBuilder<T>.hashPrimitive(crossinline valueExtractor: T.() -> Char): HashCodeBuilder<T> {
    return hashPrimitive(ToCharFunction { valueExtractor(it) })
}

@JvmName("compareChar")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Char): EqualsAndHashCodeBuilder<T> {
    return comparePrimitive(ToCharFunction { valueExtractor(it) })
}

@JvmName("compareAndHashChar")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compareAndHashPrimitive(crossinline valueExtractor: T.() -> Char): EqualsAndHashCodeBuilder<T> {
    return compareAndHashPrimitive(ToCharFunction { valueExtractor(it) })
}

@JvmName("compareInt")
inline fun <T : Any> EqualsBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Int): EqualsBuilder<T> {
    return comparePrimitive(ToIntFunction { valueExtractor(it) })
}

@JvmName("hashInt")
inline fun <T : Any> HashCodeBuilder<T>.hashPrimitive(crossinline valueExtractor: T.() -> Int): HashCodeBuilder<T> {
    return hashPrimitive(ToIntFunction { valueExtractor(it) })
}

@JvmName("compareInt")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Int): EqualsAndHashCodeBuilder<T> {
    return comparePrimitive(ToIntFunction { valueExtractor(it) })
}

@JvmName("compareAndHashInt")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compareAndHashPrimitive(crossinline valueExtractor: T.() -> Int): EqualsAndHashCodeBuilder<T> {
    return compareAndHashPrimitive(ToIntFunction { valueExtractor(it) })
}

@JvmName("compareLong")
inline fun <T : Any> EqualsBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Long): EqualsBuilder<T> {
    return comparePrimitive(ToLongFunction { valueExtractor(it) })
}

@JvmName("hashLong")
inline fun <T : Any> HashCodeBuilder<T>.hashPrimitive(crossinline valueExtractor: T.() -> Long): HashCodeBuilder<T> {
    return hashPrimitive(ToLongFunction { valueExtractor(it) })
}

@JvmName("compareLong")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Long): EqualsAndHashCodeBuilder<T> {
    return comparePrimitive(ToLongFunction { valueExtractor(it) })
}

@JvmName("compareAndHashLong")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compareAndHashPrimitive(crossinline valueExtractor: T.() -> Long): EqualsAndHashCodeBuilder<T> {
    return compareAndHashPrimitive(ToLongFunction { valueExtractor(it) })
}

@JvmName("compareFloat")
inline fun <T : Any> EqualsBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Float): EqualsBuilder<T> {
    return comparePrimitive(ToFloatFunction { valueExtractor(it) })
}

@JvmName("hashFloat")
inline fun <T : Any> HashCodeBuilder<T>.hashPrimitive(crossinline valueExtractor: T.() -> Float): HashCodeBuilder<T> {
    return hashPrimitive(ToFloatFunction { valueExtractor(it) })
}

@JvmName("compareFloat")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Float): EqualsAndHashCodeBuilder<T> {
    return comparePrimitive(ToFloatFunction { valueExtractor(it) })
}

@JvmName("compareAndHashFloat")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compareAndHashPrimitive(crossinline valueExtractor: T.() -> Float): EqualsAndHashCodeBuilder<T> {
    return compareAndHashPrimitive(ToFloatFunction { valueExtractor(it) })
}

@JvmName("compareDouble")
inline fun <T : Any> EqualsBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Double): EqualsBuilder<T> {
    return comparePrimitive(ToDoubleFunction { valueExtractor(it) })
}

@JvmName("hashDouble")
inline fun <T : Any> HashCodeBuilder<T>.hashPrimitive(crossinline valueExtractor: T.() -> Double): HashCodeBuilder<T> {
    return hashPrimitive(ToDoubleFunction { valueExtractor(it) })
}

@JvmName("compareDouble")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Double): EqualsAndHashCodeBuilder<T> {
    return comparePrimitive(ToDoubleFunction { valueExtractor(it) })
}

@JvmName("compareAndHashDouble")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compareAndHashPrimitive(crossinline valueExtractor: T.() -> Double): EqualsAndHashCodeBuilder<T> {
    return compareAndHashPrimitive(ToDoubleFunction { valueExtractor(it) })
}

@JvmName("compareBoolean")
inline fun <T : Any> EqualsBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Boolean): EqualsBuilder<T> {
    return comparePrimitive(Predicate { valueExtractor(it) })
}

@JvmName("hashBoolean")
inline fun <T : Any> HashCodeBuilder<T>.hashPrimitive(crossinline valueExtractor: T.() -> Boolean): HashCodeBuilder<T> {
    return hashPrimitive(Predicate { valueExtractor(it) })
}

@JvmName("compareBoolean")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.comparePrimitive(crossinline valueExtractor: T.() -> Boolean): EqualsAndHashCodeBuilder<T> {
    return comparePrimitive(Predicate { valueExtractor(it) })
}

@JvmName("compareAndHashBoolean")
inline fun <T : Any> EqualsAndHashCodeBuilder<T>.compareAndHashPrimitive(crossinline valueExtractor: T.() -> Boolean): EqualsAndHashCodeBuilder<T> {
    return compareAndHashPrimitive(Predicate { valueExtractor(it) })
}