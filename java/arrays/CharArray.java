package arrays;

import primitives.Char;
import template.Array;
import template.DataTypes;
import template.InvalidArgument;


public class CharArray extends Array implements DataTypes {


    public CharArray(Data[] datatypes) { super(datatypes); }

    public CharArray(int... nodes) { super(nodes); }

    @Override
    protected CharArray newArray(Data[] multiDimensions) { return new CharArray(multiDimensions); }

    @Override
    protected CharArray[] createDatatypeArray(int size) { return new CharArray[size]; }

    @Override
    protected Char[] generateBaseArray(int size) {

        Char[] temp = new Char[size];

        for (int i=0; i<size; i++) temp[i] = new Char((byte) 0);

        return temp;

    }

    @Override
    public CharArray getData() { return this; }

}
