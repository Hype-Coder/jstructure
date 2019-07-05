package arrays;

import primitives.Float;
import template.Array;
import template.DataTypes;
import template.InvalidArgument;


public class FloatArray extends Array implements DataTypes {


    public FloatArray(Data[] datatypes) { super(datatypes); }

    public FloatArray(int... nodes) { super(nodes); }

    @Override
    protected FloatArray newArray(Data[] multiDimensions) { return new FloatArray(multiDimensions); }

    @Override
    protected FloatArray[] createDatatypeArray(int size) { return new FloatArray[size]; }

    @Override
    public void set(Data value, int... nodes) throws InvalidArgument { super.set(value, nodes); }

    @Override
    public Data getData() {
        return this;
    }

    @Override
    protected Float[] generateBaseArray(int size) {

        Float[] temp = new Float[size];

        for (int i=0; i<size; i++) temp[i] = new Float((float) 0);

        return temp;

    }

}
