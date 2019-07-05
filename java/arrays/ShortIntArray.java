package arrays;

import primitives.*;
import template.Array;


public class ShortIntArray extends Array {


    public ShortIntArray(Data[] datatypes) { super(datatypes); }

    public ShortIntArray(int... nodes) { super(nodes); }

    @Override
    protected ShortIntArray newArray(Data[] multiDimensions) { return new ShortIntArray(multiDimensions); }

    @Override
    protected ShortIntArray[] createDatatypeArray(int size) { return new ShortIntArray[size]; }

    @Override
    public Data getData() { return this; }

    @Override
    protected ShortInt[] generateBaseArray(int size) {

        ShortInt[] temp = new ShortInt[size];

        for (int i=0; i<size; i++) temp[i] = new ShortInt((short)0);

        return temp;

    }


}
