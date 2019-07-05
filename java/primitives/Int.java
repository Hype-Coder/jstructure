package primitives;

import template.DataTypes;
import template.InvalidArgument;
import template.Primitive;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Int extends primitives.Number {


    private java.lang.Integer i = Primitive.default_value;


    public Int(java.lang.Integer i ) { this.i = i; }

    public Int(int i ) { this.i = i; }

    public Int() {}

    public Int(byte[] a, ByteOrder order) throws InvalidArgument { this.fromBytes(a, order); }

    public Int(byte[] array) throws InvalidArgument { this.fromBytes(array, ByteOrder.nativeOrder()); }


    @Override
    public Char charValue() { return new Char(i.byteValue()); }

    @Override
    public ShortInt shortValue() { return new ShortInt(i.shortValue()); }

    @Override
    public Int intValue() { return new Int(i); }

    @Override
    public LongLongInt longValue() { return new LongLongInt(i.longValue()); }

    @Override
    public primitives.Float floatValue() { return new primitives.Float(i.floatValue()); }

    @Override
    public primitives.Double doubleValue() { return new primitives.Double(i.doubleValue()); }

    @Override
    public java.lang.Number getPrimitive() {
        return this.i;
    }


    @Override
    public final byte[] toBytes(ByteOrder order) { return ByteBuffer.allocate(Integer.BYTES).order(order).putInt(i).array(); }

    @Override
    protected final int getTotalSpace() { return Integer.BYTES; }

    @Override
    public int length() { return Integer.BYTES; }

    @Override
    public String toString() { return i.toString(); }

    @Override
    public Class<Int> getCla$$() { return Int.class; }



    @Override
    public Int getData() { return this; }

    @Override
    protected final void fromBytes(byte[] bytes) throws InvalidArgument { this.fromBytes(bytes, ByteOrder.nativeOrder()); }

    @Override
    protected final byte[] toBytes() { return this.toBytes(ByteOrder.nativeOrder()); }

    //private static native int getintsize ();

    @Override
    protected final void fromBytes(byte[] data, ByteOrder order) throws InvalidArgument {

        if (data == null) this.i = 0x0;

        else if (data.length > 4) throw new InvalidArgument("Byte array too large to cast to Int value");

        else {

            if (data.length < 4) data = fillUp(data);

            this.i = ByteBuffer.wrap(data).order(order).getInt();

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

        return new Int(i + num.getPrimitive().intValue());

    }

    @Override
    public Number sub(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.add(this).neg();

        return new Int(i - num.getPrimitive().intValue());

    }

    @Override
    public Number molt(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.molt(this);

        return new Int(i * num.getPrimitive().intValue());

    }

    @Override
    public Number div(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.div(this).opp();

        return new Float(this.i.floatValue() / num.getPrimitive().floatValue());

    }

    @Override
    public Number neg() {

        return new Int(-this.i);

    }

    @Override
    protected Number opp() {

        return new Double( 1 / this.i);

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
