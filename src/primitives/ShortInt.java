package primitives;

import template.DataTypes;
import template.InvalidArgument;
import template.Primitive;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class ShortInt extends Number {

    private Short s = (short) Primitive.default_value;


    public ShortInt(Short s ) {

        this.s = s;

    }

    public ShortInt(short s ) {

        this.s = s;

    }

    public ShortInt() {


    }

    public ShortInt(byte[] a, ByteOrder order) throws InvalidArgument {

        this.fromBytes(a,order);

    }

    public ShortInt(byte[] array) throws InvalidArgument {

        this.fromBytes(array);

    }


    @Override
    public Char charValue() {

        return new Char(s.byteValue());

    }

    @Override
    public ShortInt shortValue() {

        return new ShortInt(s);

    }

    @Override
    public Int intValue() {

        return new Int(s.intValue());

    }

    @Override
    public LongLongInt longValue() {

        return new LongLongInt(s.longValue());

    }

    @Override
    public Float floatValue() {

        return new Float(s.floatValue());

    }

    @Override
    public Double doubleValue() {

        return new Double(s.doubleValue());

    }

    @Override
    public java.lang.Number getPrimitive() {

        return this.s;

    }

    @Override
    public final byte[] toBytes(ByteOrder order) {

        return ByteBuffer.allocate(Short.BYTES).order(order).putShort(s).array();

    }

    @Override
    protected final int getTotalSpace() {

        return Short.BYTES;

    }

    @Override
    public int length() {

        return Short.BYTES;

    }

    @Override
    public String toString() {

        return s.toString();

    }

    @Override
    public Class<ShortInt> getCla$$() {

        return ShortInt.class;

    }

    @Override
    public ShortInt getData() {

        return this;

    }

    @Override
    protected final void fromBytes(byte[] bytes) throws InvalidArgument {

        this.fromBytes(bytes, ByteOrder.nativeOrder());

    }

    @Override
    protected final byte[] toBytes() {

        return this.toBytes(ByteOrder.nativeOrder());

    }

    @Override
    protected final void fromBytes(byte[] data, ByteOrder order) throws InvalidArgument {

        if (data == null) this.s = 0x0;

        else if (data.length > 2) throw new InvalidArgument("Byte array too large to cast to Short Int value");

        else {

            if (data.length < 2) data = fillUp(data);

            this.s = ByteBuffer.wrap(data).order(order).getShort();

        }

    }

    private byte[] fillUp (byte[] data) {

        byte[] temp = new byte[2];

        for (int i=0, j=data.length - temp.length; i<temp.length; i++, j++) temp[i] = i < temp.length - data.length ? 0x0 : data[j];

        return temp;

    }

    @Override
    public Number add(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.add(this);

        return new ShortInt((short)(s + num.getPrimitive().shortValue()));

    }

    @Override
    public Number sub(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.add(this).neg();

        return new ShortInt((short)(s - num.getPrimitive().shortValue()));

    }

    @Override
    public Number molt(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.molt(this);

        return new ShortInt((short)(s * num.getPrimitive().shortValue()));

    }

    @Override
    public Number div(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.div(this).opp();

        return new Float(this.s.floatValue() / num.getPrimitive().floatValue());

    }

    @Override
    public Number neg() {

        return new ShortInt((short) -this.s);

    }

    @Override
    protected Number opp() {

        return new Double(1 / this.s);

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
