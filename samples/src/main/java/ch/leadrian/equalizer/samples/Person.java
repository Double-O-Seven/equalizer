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

package ch.leadrian.equalizer.samples;

import ch.leadrian.equalizer.Equalizer;
import ch.leadrian.equalizer.EqualsAndHashCode;

public class Person {

    /*
     * Compare age, firstName, lastName and pets, but only hash using firstName and last name.
     * Using EQUALS_AND_HASH_CODE to implement equals() and hashCode() will satisfy the requirement
     * that two instances of Person will have the same hashCode() if they are equivalent.
     */
    private static final EqualsAndHashCode<Person> EQUALS_AND_HASH_CODE = Equalizer.equalsAndHashCodeBuilder(Person.class)
            .comparePrimitive(Person::getAge)
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
