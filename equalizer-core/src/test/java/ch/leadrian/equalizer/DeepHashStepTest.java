package ch.leadrian.equalizer;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DeepHashStepTest {

    @ParameterizedTest
    @ArgumentsSource(DeepHashArgumentsProvider.class)
    void shouldReturnExpectedHashCodeOfValue(Object object, int expectedHashCode) {
        TestData testData = new TestData(object);
        DeepHashStep<TestData> hashStep = new DeepHashStep<>(TestData::getValue);

        int hashCode = hashStep.hash(testData);

        assertThat(hashCode)
                .isEqualTo(expectedHashCode);
    }

    private static class DeepHashArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    new DeepHashArguments<>(null, value -> 0),
                    new DeepHashArguments<>("test", Object::hashCode),
                    new DeepHashArguments<>(new Object[]{"foo", "bar"}, Arrays::deepHashCode),
                    new DeepHashArguments<>(new byte[]{1, 2}, Arrays::hashCode),
                    new DeepHashArguments<>(new int[]{3, 4}, Arrays::hashCode),
                    new DeepHashArguments<>(new long[]{5L, 6L}, Arrays::hashCode),
                    new DeepHashArguments<>(new char[]{'a', 'b'}, Arrays::hashCode),
                    new DeepHashArguments<>(new float[]{7f, 8f}, Arrays::hashCode),
                    new DeepHashArguments<>(new double[]{9.0, 10.0}, Arrays::hashCode),
                    new DeepHashArguments<>(new boolean[]{true, false}, Arrays::hashCode)
            );
        }
    }

    private static class DeepHashArguments<T> implements Arguments {

        private final T value;
        private final ToIntFunction<T> hashFunction;

        DeepHashArguments(T value, ToIntFunction<T> hashFunction) {
            this.value = value;
            this.hashFunction = hashFunction;
        }

        @Override
        public Object[] get() {
            return new Object[]{value, hashFunction.applyAsInt(value)};
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