package ch.leadrian.equalizer;

import org.immutables.value.Value;

@Value.Immutable
interface TestObject extends TestObjectBase {

    String getStringValue();

    Object getObjectValue();

    Object[] getArrayValue();

    int getIntValue();

    long getLongValue();

    double getDoubleValue();

    boolean getBooleanValue();
}
