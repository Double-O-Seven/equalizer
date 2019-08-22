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