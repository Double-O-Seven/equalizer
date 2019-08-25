package ch.leadrian.equalizer;

import org.immutables.value.Value;

@Value.Immutable
interface TestObject extends TestObjectBase {

    String getStringValue();

    Object getObjectValue();

    Object[] getArrayValue();

    byte getByteValue();

    short getShortValue();

    char getCharValue();

    int getIntValue();

    long getLongValue();

    float getFloatValue();

    double getDoubleValue();

    boolean getBooleanValue();
}
