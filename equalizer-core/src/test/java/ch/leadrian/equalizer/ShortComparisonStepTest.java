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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShortComparisonStepTest {

    @Test
    void givenValuesAreEqualItShouldReturnTrue() {
        ShortComparisonStep<TestData> hashStep = new ShortComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData((short) 1234), new TestData((short) 1234));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        ShortComparisonStep<TestData> hashStep = new ShortComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData((short) 1234), new TestData((short) 1337));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final short value;

        TestData(short value) {
            this.value = value;
        }

        public short getValue() {
            return value;
        }
    }

}