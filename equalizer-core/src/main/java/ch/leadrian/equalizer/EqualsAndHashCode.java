package ch.leadrian.equalizer;

/**
 * <p>
 * Combines {@link Equals} and {@link HashCode}. The combined interfaces guarantees
 * that equality checks and hashing are configured in a way such that the contracts between
 * {@link Object#equals(Object)} and {@link Object#hashCode()} are followed, meaning that if two objects are
 * equivalent, then their hash codes are the same.
 * A specific instance may be configured and instantiated using {@link Equalizer#equalsAndHashCodeBuilder(Class)} (Class)}.
 * </p>
 * Example:
 * <pre>
 *     public class Person {
 *
 *         private static final EqualsAndHashCode&lt;Person&gt; EQUALS_AND_HASH_CODE = Equalizer.equalsAndHashCodeBuilder(Person.class)
 *                 .comparePrimitive(Person::getAge)
 *                 .compareAndHash(Person::getName)
 *                 .build();
 *
 *         private final int age;
 *         private final String name;
 *
 *         public Person(int age, String name) {
 *             this.age = age;
 *             this.name = name;
 *         }
 *
 *         public int getAge() {
 *             return age;
 *         }
 *
 *         public String getName() {
 *             return name;
 *         }
 *
 *         &#64;Override
 *         public int hashCode() {
 *             return EQUALS_AND_HASH_CODE.hashCode(this);
 *         }
 *
 *         &#64;Override
 *         public boolean equals(Object object) {
 *             return EQUALS_AND_HASH_CODE.equals(this, object);
 *         }
 *     }
 * </pre>
 *
 * @param <T> Type for which {@link EqualsAndHashCode} has been configured.
 * @see HashCode
 * @see Equals
 * @see EqualsAndHashCodeBuilder
 */
public interface EqualsAndHashCode<T> extends Equals<T>, HashCode<T> {
}
