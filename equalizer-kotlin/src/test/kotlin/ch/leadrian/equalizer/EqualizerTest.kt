package ch.leadrian.equalizer

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class EqualizerTest {

    @Nested
    inner class EqualsTests {

        @Test
        fun `should use int value to compare objects`() {
            val equals = equals<TestObject> {
            }
        }
    }

}