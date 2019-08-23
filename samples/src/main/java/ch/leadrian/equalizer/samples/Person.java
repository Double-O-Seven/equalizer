package ch.leadrian.equalizer.samples;

import ch.leadrian.equalizer.Equalizer;
import ch.leadrian.equalizer.EqualsAndHashCode;

public class Person {

    /*
     * Compare age, firstName, lastName and children, but only hash using firstName and last name.
     * Using EQUALS_AND_HASH_CODE to implement equals() and hashCode() will satisfy the requirement
     * that two instances of Person will have the same hashCode() if they are equivalent.
     */
    private static final EqualsAndHashCode<Person> EQUALS_AND_HASH_CODE = Equalizer.equalsAndHashCodeBuilder(Person.class)
            .compare(Person::getAge)
            .compareAndHash(Person::getFirstName)
            .compareAndHash(Person::getLastName)
            .compareDeep(Person::getPets)
            .build();

    private final int age;
    private final String firstName;
    private final String lastName;
    private final Animal[] pets;

    public Person(int age, String firstName, String lastName, Animal[] pets) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pets = pets;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Animal[] getPets() {
        return pets;
    }

    @Override
    public int hashCode() {
        return EQUALS_AND_HASH_CODE.hashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EQUALS_AND_HASH_CODE.equals(this, obj);
    }
}
