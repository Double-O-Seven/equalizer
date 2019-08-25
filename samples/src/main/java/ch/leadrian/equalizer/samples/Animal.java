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
