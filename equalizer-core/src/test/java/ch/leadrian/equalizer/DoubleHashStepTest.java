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

class DoubleHashStepTest {

    @Test
    void shouldReturnHashCode() {
        DoubleHashStep<TestData> hashStep = new DoubleHashStep<>(TestData::getValue);

        int result = hashStep.hash(new TestData(1337.0));

        assertThat(result)
                .isEqualTo(Double.hashCode(1337.0));
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