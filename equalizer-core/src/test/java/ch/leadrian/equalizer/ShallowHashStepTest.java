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

class ShallowHashStepTest {

    @Test
    void givenNullItShouldReturnZero() {
        TestData testData = new TestData(null);
        ShallowHashStep<TestData> hashStep = new ShallowHashStep<>(TestData::getValue);

        int hashCode = hashStep.hash(testData);

        assertThat(hashCode)
                .isZero();
    }

    @Test
    void givenNonNullValueItShouldReturnHashCodeOfValue() {
        String value = "test";
        TestData testData = new TestData(value);
        ShallowHashStep<TestData> hashStep = new ShallowHashStep<>(TestData::getValue);

        int hashCode = hashStep.hash(testData);

        assertThat(hashCode)
                .isEqualTo(value.hashCode());
    }

    private static class TestData {

        private final String value;

        TestData(String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }
    }

}