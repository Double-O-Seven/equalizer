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

package ch.leadrian.equalizer

private val TEST_ARRAY = arrayOf<Any>()

private object Singleton

@Suppress("ArrayInDataClass")
internal data class TestObject(
        val stringValue: String = "",
        val objectValue: Any = Singleton,
        val arrayValue: Array<Any> = TEST_ARRAY,
        val byteValue: Byte = 0,
        val shortValue: Short = 0,
        val charValue: Char = 'a',
        val intValue: Int = 0,
        val longValue: Long = 0L,
        val floatValue: Float = 0f,
        val doubleValue: Double = 0.0,
        val booleanValue: Boolean = false
)