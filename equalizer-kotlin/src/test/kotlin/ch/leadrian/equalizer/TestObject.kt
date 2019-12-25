package ch.leadrian.equalizer

@Suppress("ArrayInDataClass")
internal data class TestObject(
        val stringValue: String = "",
        val objectValue: Any = Any(),
        val arrayValue: Array<Any> = arrayOf(),
        val byteValue: Byte = 0,
        val shortValue: Short = 0,
        val charValue: Char = 'a',
        val intValue: Int = 0,
        val longValue: Long = 0L,
        val floatValue: Float = 0f,
        val doubleValue: Double = 0.0,
        val booleanValue: Boolean = false
)