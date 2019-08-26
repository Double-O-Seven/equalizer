/*
 * Copyright (C) 2019 Adrian-Philipp Leuenberger
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
import ch.leadrian.equalizer.Equals;
import ch.leadrian.equalizer.HashCode;

public class Dog extends Animal {

    private static final Equals<Dog> EQUALS = Equalizer.equalsBuilder(Dog.class)
            .withSuper(Animal.EQUALS)
            .compare(Dog::getBreed)
            .build();

    private static final HashCode<Dog> HASH_CODE = Equalizer.<Dog>hashCodeBuilder()
            .withSuper(Animal.HASH_CODE)
            .hash(Dog::getBreed)
            .build();

    private final String breed;

    public Dog(int age, String name, String breed) {
        super(age, name);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public int hashCode() {
        return HASH_CODE.hashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EQUALS.equals(this, obj);
    }
}
