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

You may also use it in Kotlin, in case data classes are not an option are you are required to use an array in `equals` or `hashCode`:

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

## API

### Java

The basic API provides the following classes:

*   `Equalizer`: A class containing factory methods to instantiate `EqualsBuilder`s, `HashCodeBuilder`s and `EqualsAndHashCodeBuilder`s.
*   `Equals`: Delegate used to implement `equals()`
*   `EqualsBuilder`: Fluent builder for `Equals`
*   `HashCode`: Delegate used to implement `hashCode()`
*   `HashCodeBuilder`: Fluent builder for `HashCode`
*   `EqualsAndHashCode`: Combination of `Equals` and `HashCode` honouring the contracts between `equals()` and `hashCode()`
*   `EqualsAndHashCodeBuilder`: Fluent builder for `EqualsAndHashCode`
*   `ClassMatcher`: Matcher to determine whether two instances of a class can be compared
*   `ClassMatchers`: A class containing factory methods for common `ClassMatcher`s
  
`EqualsBuilder`  and `EqualsAndHashCodeBuilder` contain various methods to compare various types of attributes:
*   `compare`: Compare reference values
*   `comparePrimitive`: Compare primitive values
*   `compareDeep`: Compare contents of arrays
*   `compareIdentity`: Compare reference values using reference equality
*   `equalIf`: Add custom conditions for equality
*   `withSuper`: Use the `Equals` or `EqualsAndHashCode` instances of a superclass
*   `classMatcher`: Configure, which instances of a class can be compared
  
`HashCodeBuilder` contains various methods to hash various types of attributes:
*   `hash`: Use reference values for hashing
*   `hashPrimitive`: Use a primitive values for hashing
*   `hashDeep`: Use contents of arrays for hashing
*   `hashIdentity`: Use identity hash codes for hashing
*   `withSuper`: Use `HashCode` instances of a superclass for hashing
  
`EqualsAndHashCodeBuilder` also includes the following methods to use the same attributes for equality checks and hashing in order to honour the contracts between `equals()` and `hashCode()`:
*   `compareAndHash`: Compare and hash reference values
*   `compareAndHashPrimitive`: Compare and hash primitive values
*   `compareAndHashDeep`: Compare and hash contents of arrays
*   `compareAndHashIdentity`: Compare reference values using reference equality and use identity hash codes for hashing

`Equals`, `HashCode` and `EqualsAndHashCode` also contain factory methods for simple instantation without using a builder:
*   `Equals.of`
*   `HashCode.of`
*   `EqualsAndHashCode.of`

### Kotlin (since 1.2.0)

There is also a simpler Kotlin DSL:

`KotlinEqualsBuilder` includes inline variants of the `compare` methods of `EqualsBuilder`. All functions accept a lambda function with a receiver:
*   `compare`
*   `compareIdentity`
*   `compareDeep`
*   `compareByte`: Inlined variant of `comparePrimitive` for byte values
*   `compareShort`: Inlined variant of `comparePrimitive` for short values
*   `compareChar`: Inlined variant of `comparePrimitive` for char values
*   `compareInt`: Inlined variant of `comparePrimitive` for int values
*   `compareLong`: Inlined variant of `comparePrimitive` for long values
*   `compareFloat`: Inlined variant of `comparePrimitive` for float values
*   `compareDouble`: Inlined variant of `comparePrimitive` for double values
*   `compareBoolean`: Inlined variant of `comparePrimitive` for boolean values

`KotlinHashCodeBuilder` includes inline variants of the `hash` methods of `HashCodeBuilder`. All functions accept a lambda function with a receiver:
*   `hash`
*   `hashIdentity`
*   `hashDeep`
*   `hashByte`: Inlined variant of `hashPrimitive` for byte values
*   `hashShort`: Inlined variant of `hashPrimitive` for short values
*   `hashChar`: Inlined variant of `hashPrimitive` for char values
*   `hashInt`: Inlined variant of `hashPrimitive` for int values
*   `hashLong`: Inlined variant of `hashPrimitive` for long values
*   `hashFloat`: Inlined variant of `hashPrimitive` for float values
*   `hashDouble`: Inlined variant of `hashPrimitive` for double values
*   `hashBoolean`: Inlined variant of `hashPrimitive` for boolean values

`KotlinEqualsAndHashCodeBuilder` includes inline variants of the `compare` methods of `EqualsAndHashCodeBuilder`. All functions accept a lambda function with a receiver:
*   `compare`
*   `compareIdentity`
*   `compareDeep`
*   `compareByte`: Inlined variant of `comparePrimitive` for byte values
*   `compareShort`: Inlined variant of `comparePrimitive` for short values
*   `compareChar`: Inlined variant of `comparePrimitive` for char values
*   `compareInt`: Inlined variant of `comparePrimitive` for int values
*   `compareLong`: Inlined variant of `comparePrimitive` for long values
*   `compareFloat`: Inlined variant of `comparePrimitive` for float values
*   `compareDouble`: Inlined variant of `comparePrimitive` for double values
*   `compareBoolean`: Inlined variant of `comparePrimitive` for boolean values
*   `compareAndHash`
*   `compareAndHashIdentity`
*   `compareAndHashDeep`
*   `compareAndHashByte`: Inlined variant of `compareAndHashPrimitive` for byte values
*   `compareAndHashShort`: Inlined variant of `compareAndHashPrimitive` for short values
*   `compareAndHashChar`: Inlined variant of `compareAndHashPrimitive` for char values
*   `compareAndHashInt`: Inlined variant of `compareAndHashPrimitive` for int values
*   `compareAndHashLong`: Inlined variant of `compareAndHashPrimitive` for long values
*   `compareAndHashFloat`: Inlined variant of `compareAndHashPrimitive` for float values
*   `compareAndHashDouble`: Inlined variant of `compareAndHashPrimitive` for double values
*   `compareAndHashBoolean`: Inlined variant of `compareAndHashPrimitive` for boolean values

Scoped builder functions:
*   `equals`: Function that allows `this`-scoped configuration of a `KotlinEqualsBuilder`
*   `hashCode`: Function that allows `this`-scoped configuration of a `KotlinHashCodeBuilder`
*   `equalsAndHashCode`: Function that allows `this`-scoped configuration of a `KotlinEqualsAndHashCodeBuilder`

`Equals` and `HashCode` and by inheritance `EqualsAndHashCode` are extended with the following extension methods:
*   `Equals.invoke`
*   `HashCode.invoke`

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
