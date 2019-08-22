package ch.leadrian.equalizer;

interface ComparisonStep<T> {

    boolean isEqual(T object1, T object2);

}
