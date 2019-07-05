package primitives;

import template.DataTypes;
import template.InvalidArgument;
import template.Primitive;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public final class Double extends primitives.Number {

    private java.lang.Double d = (double) Primitive.default_value;


    public Double(java.lang.Double d ) { this.d = d; }

    public Double(double d ) { this.d = d; }

    public Double(byte[] a, ByteOrder order) throws InvalidArgument { this.fromBytes(a,order); }

    public Double(byte[] a) throws InvalidArgument { this.fromBytes(a); }

    public Double() {}



    @Override
    public Char charValue() {

        return new Char(d.byteValue());

    }

    @Override
    public ShortInt shortValue() {

        return new ShortInt(d.shortValue());

    }

    @Override
    public Int intValue() {

        return new Int(d.intValue());

    }

    @Override
    public LongLongInt longValue() {

        return new LongLongInt(d.longValue());

    }

    @Override
    public primitives.Float floatValue() {

        return new primitives.Float(d.floatValue());

    }

    @Override
    public primitives.Double doubleValue() {

        return new primitives.Double(d);

    }

    @Override
    public java.lang.Number getPrimitive() {

        return this.d;

    }

    @Override
    public final byte[] toBytes(ByteOrder order) {

        return ByteBuffer.allocate(java.lang.Double.BYTES).order(order).putDouble(d).array();

    }

    @Override
    protected final int getTotalSpace() {

        return java.lang.Double.BYTES;

    }

    @Override
    public int length() {

        return java.lang.Double.BYTES;

    }

    @Override
    public String toString() {

        return d.toString();

    }

    @Override
    public Class<Double> getCla$$() {

        return Double.class;

    }

    @Override
    public Number add(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.add(this);

        return new Double(d + num.getPrimitive().doubleValue());

    }

    @Override
    public Number sub(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.add(this).neg();

        return new Double(d - num.getPrimitive().doubleValue());

    }

    @Override
    public Number molt(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.molt(this);

        return new Double(d * num.getPrimitive().doubleValue());

    }

    @Override
    public Number div(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.div(this).opp();

        return new Double(d / num.getPrimitive().doubleValue());

    }

    @Override
    public Number neg() {

        return new Double(-this.d);

    }

    @Override
    protected Number opp() {

        return new Double(1 / this.d);

    }

    @Override
    public Double getData() {

        return this;

    }

    @Override
    protected final void fromBytes(byte[] bytes) throws InvalidArgument {

        fromBytes(bytes, ByteOrder.nativeOrder());

    }

    @Override
    protected final byte[] toBytes() {

        return toBytes(ByteOrder.nativeOrder());

    }

    @Override
    protected final void fromBytes(byte[] data, ByteOrder order) throws InvalidArgument {

        if (data == null) this.d = (double) 0x0;

        else if (data.length > 8) throw new InvalidArgument("Byte array too large to cast to Double value");

        else {

            if (data.length < 8) data = fillUp(data);

            this.d = ByteBuffer.wrap(data).order(order).getDouble();  //java.lang.Double.longBitsToDouble(LongLongInt.fromByteArray(data));

        }

    }

    private byte[] fillUp (byte[] data) {

        byte[] temp = new byte[8];

        for (int i=0, j=data.length - temp.length; i<temp.length; i++, j++) temp[i] = i < temp.length - data.length ? 0x0 : data[j];

        return temp;

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
