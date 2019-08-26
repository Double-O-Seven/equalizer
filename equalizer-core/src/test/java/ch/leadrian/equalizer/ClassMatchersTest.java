package ch.leadrian.equalizer;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClassMatchersTest {

    @Nested
    class InstanceOfTests {

        @Test
        void givenBothValuesAreInstancesOfTargetClassItShouldReturnTrue() {
            ClassMatcher<CharSequence> classMatcher = ClassMatchers.instanceOf(CharSequence.class);

            boolean result = classMatcher.classesMatch("1234", new StringBuilder());

            assertThat(result)
                    .isTrue();
        }

        @Test
        void givenSecondValueIsNotInstanceOfTargetClassItShouldReturnFalse() {
            ClassMatcher<CharSequence> classMatcher = ClassMatchers.instanceOf(CharSequence.class);

            boolean result = classMatcher.classesMatch("1234", 1234);

            assertThat(result)
                    .isFalse();
        }

    }

    @Nested
    class SameClassTests {

        @Test
        void givenBothValuesAreInstancesOfSameClassItShouldReturnTrue() {
            ClassMatcher<CharSequence> classMatcher = ClassMatchers.sameClass();

            boolean result = classMatcher.classesMatch("1234", "hahaha");

            assertThat(result)
                    .isTrue();
        }

        @Test
        void givenOneValueIsNotInstanceOfTargetClassItShouldReturnFalse() {
            ClassMatcher<CharSequence> classMatcher = ClassMatchers.sameClass();

            boolean result = classMatcher.classesMatch("1234", new StringBuilder());

            assertThat(result)
                    .isFalse();
        }

    }

    @Nested
    class ExactClassTests {

        @Test
        void givenBothValuesAreDirectInstancesOfClassItShouldReturnTrue() {
            ClassMatcher<String> classMatcher = ClassMatchers.exactClass(String.class);

            boolean result = classMatcher.classesMatch("1234", "hahaha");

            assertThat(result)
                    .isTrue();
        }

        @Test
        void givenFirstValuesIsNotDirectInstanceOfTargetClassItShouldReturnFalse() {
            ClassMatcher<Foo> classMatcher = ClassMatchers.exactClass(Foo.class);

            boolean result = classMatcher.classesMatch(new Bar(), new Foo());

            assertThat(result)
                    .isFalse();
        }

        @Test
        void givenSecondValuesIsNotDirectInstanceOfTargetClassItShouldReturnFalse() {
            ClassMatcher<Foo> classMatcher = ClassMatchers.exactClass(Foo.class);

            boolean result = classMatcher.classesMatch(new Foo(), new Bar());

            assertThat(result)
                    .isFalse();
        }

    }

    private static class Foo {
    }

    private static class Bar extends Foo {
    }


}