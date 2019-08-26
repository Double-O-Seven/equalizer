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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DoubleComparisonStepTest {

    @ParameterizedTest
    @CsvSource({
            "1234.0, 1234.0",
            "NaN, NaN"
    })
    void givenValuesAreEqualItShouldReturnTrue(double value1, double value2) {
        DoubleComparisonStep<TestData> hashStep = new DoubleComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(value1), new TestData(value2));

        assertThat(result)
                .isTrue();
    }

    @Test
    void givenValuesAreNotEqualItShouldReturnFalse() {
        DoubleComparisonStep<TestData> hashStep = new DoubleComparisonStep<>(TestData::getValue);

        boolean result = hashStep.isEqual(new TestData(1234.0), new TestData(1337.0));

        assertThat(result)
                .isFalse();
    }

    private static class TestData {

        private final double value;

        TestData(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }

}