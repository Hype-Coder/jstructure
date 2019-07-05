package arrays;

import primitives.LongLongInt;
import template.Array;


public class LongLongIntArray extends Array {


    public LongLongIntArray(Data[] datatypes) { super(datatypes); }

    public LongLongIntArray(int... nodes) { super(nodes); }

    @Override
    protected LongLongIntArray newArray(Data[] multiDimensions) { return new LongLongIntArray(multiDimensions); }

    @Override
    protected LongLongIntArray[] createDatatypeArray(int size) { return new LongLongIntArray[size]; }

    @Override
    public Data getData() { return this; }

    @Override
    protected LongLongInt[] generateBaseArray(int size) {

        LongLongInt[] temp = new LongLongInt[size];

        for (int i=0; i<size; i++) temp[i] = new LongLongInt((byte) 0);

        return temp;

    }


}
