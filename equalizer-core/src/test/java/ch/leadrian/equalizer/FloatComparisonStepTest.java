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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FloatComparisonStepTest {

    @ParameterizedTest
    @CsvSource({
            "1234.0, 1234.0",
            "NaN, NaN"
    })
    void givenValuesAreEqualItShouldReturnTrue(float value1, float value2) {
        FloatComparisonStep<TestData> hashStep = new FloatComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(value1), new TestData(value2));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        FloatComparisonStep<TestData> hashStep = new FloatComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(1234.0f), new TestData(1337.0f));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final float value;

        TestData(float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }
    }

}