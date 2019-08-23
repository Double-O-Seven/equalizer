package ch.leadrian.equalizer;

/**
 * <p>
 * Hash code computation for {@link Object#hashCode()}.
 * A specific instance may be configured and instantiated using {@link Equalizer#hashCode()}.
 * </p>
 * <p>
 * {@link Equals} and {@link HashCode} may be combined using {@link EqualsAndHashCode}.
 * </p>
 * Example:
 * <pre>
 *     public class Person {
 *
 *         private static final HashCode&lt;Person&gt; HASH_CODE = Equalizer.&lt;Person&gt;hashCodeBuilder()
 *                 .hash(Person::getAge)
 *                 .hash(Person::getName)
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
 *             return HASH_CODE.hashCode(this);
 *         }
 *     }
 * </pre>
 *
 * @param <T> Type for which {@link HashCode} has been configured.
 * @see Equals
 * @see EqualsAndHashCode
 * @see HashCodeBuilder
 */
public interface HashCode<T> {

    int hashCode(T object);

}
