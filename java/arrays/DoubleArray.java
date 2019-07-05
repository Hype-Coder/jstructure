package arrays;

import primitives.Double;
import template.Array;
import template.DataTypes;
import template.InvalidArgument;


public class DoubleArray extends Array implements DataTypes {


    public DoubleArray(Data[] datatypes) { super(datatypes); }

    public DoubleArray(int... nodes) { super(nodes); }

    @Override
    protected DoubleArray newArray(Data[] multiDimensions) { return new DoubleArray(multiDimensions); }

    @Override
    protected DoubleArray[] createDatatypeArray(int size) { return new DoubleArray[size]; }

    @Override
    public void set(Data value, int... nodes) throws InvalidArgument { super.set(value, nodes); }

    @Override
    public Data getData() { return this; }

    @Override
    protected Double[] generateBaseArray(int size) {

        Double[] temp = new Double[size];

        for (int i=0; i<size; i++) temp[i] = new Double((double) 0);

        return temp;

    }


}
