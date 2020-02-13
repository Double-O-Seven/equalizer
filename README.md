[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6e4827b909274d13a109be0d47dcf4d8)](https://www.codacy.com/app/Double-O-Seven/equalizer?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Double-O-Seven/equalizer&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Double-O-Seven/equalizer.svg?branch=master)](https://travis-ci.org/Double-O-Seven/equalizer)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/6e4827b909274d13a109be0d47dcf4d8)](https://www.codacy.com/app/Double-O-Seven/equalizer?utm_source=github.com&utm_medium=referral&utm_content=Double-O-Seven/equalizer&utm_campaign=Badge_Coverage)
[![Release Version](https://img.shields.io/maven-central/v/ch.leadrian.equalizer/equalizer-core.svg?label=release)](https://search.maven.org/search?q=g:ch.leadrian.equalizer%20AND%20a:equalizer-core)

# Equalizer

A small library to greatly simplify the implementation of `equals()` and `hashCode()` in Java classes (and Kotlin classes, if data classes are not enough).
Java 8 or newer is required though.

## Why Equalizer

*   Fluent, highly readable API for `EqualsBuilder`, `HashCodeBuilder` and `EqualsAndHashCodeBuilder`.
*   Garbage-free implementations of `equals()` and `hashCode()`: If used correctly, Equalizer does not generate any garbage like `Iterator`s or boxed primitives.
*   Configure one static instance of `Equals` and `HashCode` or `EqualsAndHashCode` and reuse it across all instances to minimize garbage.
*   Use Java 8 lambdas to define which attributes should be compared and hashed.
*   Easily honour the contracts between `equals()` and `hashCode()` without a lot of boilerplate code.
*   No dependencies to other libraries

## Examples

Using Equalizer is very easy.

Configure one `EqualsAndHashCode` to use it in both `equals()` and `hashCode()` to make sure that the contracts between the two methods are always honoured.

```java
package org.mycompany;

import ch.leadrian.equalizer.Equalizer;
import ch.leadrian.equalizer.EqualsAndHashCode;

public class Person {

    private static final EqualsAndHashCode<Person> EQUALS_AND_HASH_CODE =
            Equalizer.equalsAndHashCodeBuilder(Person.class)
                    .compareAndHashPrimitive(Person::getAge)
                    .compare(Person::getName)
                    .build();

    private final int age;
    private final String name;

    public Person(int age, String name) {
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
        return EQUALS_AND_HASH_CODE.hashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EQUALS_AND_HASH_CODE.equals(this, obj);
    }
}
```

Or configure `Equals` and `HashCode` separately:

```java
package org.mycompany;

import ch.leadrian.equalizer.Equalizer;
import ch.leadrian.equalizer.Equals;
import ch.leadrian.equalizer.HashCode;

public class Person {

    private static final Equals<Person> EQUALS = 
            Equalizer.equalsBuilder(Person.class)
                    .comparePrimitive(Person::getAge)
                    .compare(Person::getName)
                    .build();

    private static final HashCode<Person> HASH_CODE = 
            Equalizer.hashCodeBuilder()
                    .hashPrimitive(Person::getAge)
                    .build();

    private final int age;
    private final String name;

    public Person(int age, String name) {
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
```

You may also use it in Kotlin, in case data classes are not an option or you are required to use an array in `equals` or `hashCode`:

```kotlin
package org.mycompany

import ch.leadrian.equalizer.equals
import ch.leadrian.equalizer.hashCode
import ch.leadrian.equalizer.invoke

class Book(
        val title: String,
        val numberOfPages: Int,
        val authors: Array<String>
) {

    companion object {

        private val EQUALS = equals<Book> {
            compare { title }
            compareInt { numberOfPages }
            compareDeep { authors }
        }

        private val HASH_CODE = hashCode<Book> {
            hash { title }
            hashInt { numberOfPages }
            hashDeep { authors }
        }

    }

    override fun equals(other: Any?): Boolean = EQUALS(this, other)

    override fun hashCode(): Int = HASH_CODE(this)

}
```

## Download

For Maven:
```xml
<dependency>
  <groupId>ch.leadrian.equalizer</groupId>
  <artifactId>equalizer-core</artifactId>
  <version>1.1.0</version>
</dependency>
```

For Gradle (Groovy DSL):
```groovy
implementation 'ch.leadrian.equalizer:equalizer-core:1.1.0'
```

For Gradle (Kotlin DSL):
```groovy
implementation("ch.leadrian.equalizer:equalizer-core:1.1.0")
```
