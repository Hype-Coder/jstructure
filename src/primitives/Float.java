package primitives;

import template.DataTypes;
import template.InvalidArgument;
import template.Primitive;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Float extends Number {

    private java.lang.Float f = (float) Primitive.default_value;


    public Float(java.lang.Float f ) { this.f = f; }

    public Float(float f ) { this.f = f; }

    public Float() {}

    public Float(byte[] a, ByteOrder order) throws InvalidArgument { this.fromBytes(a,order); }

    public Float(byte[] array) throws InvalidArgument { this.fromBytes(array); }


    @Override
    public Char charValue() { return new Char(f.byteValue()); }

    @Override
    public ShortInt shortValue() { return new ShortInt(f.shortValue()); }

    @Override
    public Int intValue() { return new Int(f.intValue()); }

    @Override
    public LongLongInt longValue() { return new LongLongInt(f.longValue()); }

    @Override
    public Float floatValue() { return new Float(f); }

    @Override
    public Double doubleValue() { return new Double(f.doubleValue()); }

    @Override
    public java.lang.Number getPrimitive() {
        return this.f;
    }

    @Override
    public final byte[] toBytes (ByteOrder order) { return ByteBuffer.allocate(java.lang.Float.BYTES).order(order).putFloat(f).array(); }

    @Override
    protected final int getTotalSpace() { return java.lang.Float.BYTES; }

    @Override
    public int length() { return java.lang.Float.BYTES; }

    @Override
    public String toString() { return f.toString(); }

    @Override
    public Class<Float> getCla$$() { return Float.class; }



    @Override
    public Float getData() { return this; }

    @Override
    protected final void fromBytes(byte[] bytes) throws InvalidArgument { this.fromBytes(bytes, ByteOrder.nativeOrder()); }

    @Override
    protected final byte[] toBytes() { return this.toBytes(ByteOrder.nativeOrder()); }

    @Override
    protected final void fromBytes(byte[] data, ByteOrder order) throws InvalidArgument {

        if (data == null) this.f = (float) 0x0;

        else if (data.length > 4) throw new InvalidArgument("Byte array too large to cast to Float value");

        else {

            if (data.length < 4) data = fillUp(data);

            this.f = ByteBuffer.wrap(data).order(order).getFloat();

        }

    }



    private byte[] fillUp (byte[] data) {

        byte[] temp = new byte[4];

        for (int i=0, j=data.length - temp.length; i<temp.length; i++, j++) temp[i] = i < temp.length - data.length ? 0x0 : data[j];

        return temp;

    }


    @Override
    public Number add(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.add(this);

        return new Float(f + num.getPrimitive().floatValue());

    }

    @Override
    public Number sub(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.add(this).neg();

        return new Float(f - num.getPrimitive().floatValue());

    }

    @Override
    public Number molt(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.molt(this);

        return new Float(f * num.getPrimitive().floatValue());

    }

    @Override
    public Number div(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.div(this).opp();

        return new Float(this.f / num.getPrimitive().floatValue());

    }

    @Override
    public Number neg() {

        return new Float(-this.f);

    }

    @Override
    protected Number opp() {

        return new Double(1 / this.f);

    }

    @Override
    public int compareTo(Object o) {

        if (!(o instanceof Number)) throw new IllegalArgumentException();

        int res = this.sub((Number) o).getPrimitive().intValue();

        if (res > 0) return 1;

        else if (res < 0) return -1;

        else return 0;

    }

}
