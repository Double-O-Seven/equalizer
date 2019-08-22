package ch.leadrian.equalizer;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DeepComparisonStepTest {

    @ParameterizedTest
    @ArgumentsSource(EquivalentValuesArgumentsProvider.class)
    void givenEquivalentValueItShouldReturnTrue(Object value1, Object value2) {
        TestData testData1 = new TestData(value1);
        TestData testData2 = new TestData(value2);
        DeepComparisonStep<TestData> comparisonStep = new DeepComparisonStep<>(TestData::getValue);

        boolean result = comparisonStep.isEqual(testData1, testData2);

        assertThat(result)
                .isTrue();
    }

    @ParameterizedTest
    @ArgumentsSource(NotEquivalentValuesArgumentsProvider.class)
    void givenNotEquivalentValueItShouldReturnTrue(Object value1, Object value2) {
        TestData testData1 = new TestData(value1);
        TestData testData2 = new TestData(value2);
        DeepComparisonStep<TestData> comparisonStep = new DeepComparisonStep<>(TestData::getValue);

        boolean result = comparisonStep.isEqual(testData1, testData2);

        assertThat(result)
                .isFalse();
    }

    private static class EquivalentValuesArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments("Test", "Test"),
                    arguments(new Integer("1"), 1),
                    () -> {
                        Object[] value1 = {"foo"};
                        Object[] value2 = {"foo"};
                        return new Object[]{value1, value2};
                    },
                    () -> {
                        Object[] value = {"foo"};
                        return new Object[]{value, value};
                    }
            );
        }
    }

    private static class NotEquivalentValuesArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments("Test", "Bar"),
                    arguments(1, 2),
                    arguments(new Object[]{1, 2}, new byte[]{1, 2, 3})
            );
        }
    }

    private static class TestData {

        private final Object value;

        TestData(Object value) {
            this.value = value;
        }

        Object getValue() {
            return value;
        }
    }

}