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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class DelegatingComparisonStepTest {

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void shouldReturnTrueIfAndOnlyIfDelegateReturnsTrue(boolean expectedResult) {
        DelegatingComparisonStep<Object> comparisonStep = new DelegatingComparisonStep<>((value1, value2) -> expectedResult);

        boolean result = comparisonStep.isEqual("Hi", "there");

        assertThat(result)
                .isEqualTo(expectedResult);
    }

}