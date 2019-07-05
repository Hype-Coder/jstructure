package arrays;

import primitives.*;
import template.Array;
import template.DataTypes;


public class IntArray extends Array implements DataTypes {


    public IntArray(Data[] datatypes) { super(datatypes); }

    public IntArray(int... nodes) { super(nodes); }

    @Override
    protected IntArray newArray(Data[] multiDimensions) { return new IntArray(multiDimensions); }

    @Override
    protected IntArray[] createDatatypeArray(int size) { return new IntArray[size]; }

    @Override
    public Data getData() { return this; }

    @Override
    protected Int[] generateBaseArray(int size) {

        Int[] temp = new Int[size];

        for (int i=0; i<size; i++) temp[i] = new Int(0);

        return temp;

    }
}
