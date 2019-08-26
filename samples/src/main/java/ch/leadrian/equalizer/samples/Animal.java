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

public class Animal {

    protected static final Equals<Animal> EQUALS = Equalizer.equalsBuilder(Animal.class)
            .comparePrimitive(Animal::getAge)
            .compare(Animal::getName)
            .build();

    protected static final HashCode<Animal> HASH_CODE = Equalizer.<Animal>hashCodeBuilder()
            .hash(Animal::getName)
            .build();

    private final int age;
    private final String name;

    public Animal(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
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
