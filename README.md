[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6e4827b909274d13a109be0d47dcf4d8)](https://www.codacy.com/app/Double-O-Seven/equalizer?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Double-O-Seven/equalizer&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/Double-O-Seven/kamp.svg?branch=master)](https://travis-ci.org/Double-O-Seven/kamp)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/6e4827b909274d13a109be0d47dcf4d8)](https://www.codacy.com/app/Double-O-Seven/equalizer?utm_source=github.com&utm_medium=referral&utm_content=Double-O-Seven/equalizer&utm_campaign=Badge_Coverage)

# Equalizer

A small library to greatly simplify the implementation of `equals()` and `hashCode()` in Java classes (and Kotlin classes, if data classes are not enough).
Java 8 or newer is required though.

Why Equalizer?
--------------

* Fluent, highly readable API for `EqualsBuilder`, `HashCodeBuilder` and `EqualsAndHashCodeBuilder`.
* Garbage-free implementations of `equals()` and `hashCode()`: If used correctly, Equalizer does not generate any garbage like `Iterator`s or boxed primitives.
* Configure one static instance of `Equals` and `HashCode` or `EqualsAndHashCode` and reuse it across all instances to minimize garbage.
* Use Java 8 lambdas to define which attributes should be compared and hashed.
* Easily honour the contracts between `equals()` and `hashCode()` without a lot of boilerplate code.
