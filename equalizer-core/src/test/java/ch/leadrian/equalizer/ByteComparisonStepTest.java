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

package ch.leadrian.equalizer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ByteComparisonStepTest {

    @Test
    void givenValuesAreEqualItShouldReturnTrue() {
        ByteComparisonStep<TestData> hashStep = new ByteComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData((byte) 5), new TestData((byte) 5));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        ByteComparisonStep<TestData> hashStep = new ByteComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData((byte) 4), new TestData((byte) 5));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final byte value;

        TestData(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }
    }

}