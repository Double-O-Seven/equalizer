package ch.leadrian.equalizer;

/**
 * <p>
 * Equivalence check for {@link java.lang.Object#equals(Object)}.
 * A specific instance may be configured and instantiated using {@link Equalizer#equalsBuilder(Class)}.
 * </p>
 * <p>
 * {@link Equals} and {@link HashCode} may be combined using {@link EqualsAndHashCode}.
 * </p>
 * Example:
 * <pre>
 *     public class Person {
 *
 *         private static final Equals&lt;Person&gt; EQUALS = Equalizer.equalsBuilder(Person.class)
 *                 .compare(Person::getAge)
 *                 .compare(Person::getName)
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
 *         public boolean equals(Object object) {
 *             return EQUALS.equals(this, object);
 *         }
 *     }
 * </pre>
 *
 * @param <T> Type for which {@link Equals} has been configured.
 * @see HashCode
 * @see EqualsAndHashCode
 * @see EqualsBuilder
 */
public interface Equals<T> {

    /**
     * @param object      Instance of the class for which {@link java.lang.Object#equals(Object)} has been implemented
     *                    using {@link Equals}.
     * @param otherObject The nullable value with which {@code object} is compared.
     * @return {@code true} if and only if {@code object} is equivalent to {@code otherObject} according to the
     * specification of {@link java.lang.Object#equals(Object)}, else {@code false}.
     * @see java.lang.Object#equals(Object)
     * @see Equalizer#equalsBuilder(Class)
     */
    boolean equals(T object, Object otherObject);

}
