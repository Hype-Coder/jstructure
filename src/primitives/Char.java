package primitives;

import template.DataTypes;
import template.Primitive;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public final class Char extends Number {


    private Byte b = (byte) Primitive.default_value;


    public Char(byte b) {

        this.b = b;

    }

    public Char(Byte b) {

        this.b = b;

    }

    public Char() {
    
    }


    @Override
    public Char charValue() {

        return this;

    }

    @Override
    public ShortInt shortValue() {

        return new ShortInt(b.shortValue());

    }

    @Override
    public Int intValue() {

        return new Int(b.intValue());

    }

    @Override
    public LongLongInt longValue() {

        return new LongLongInt(b.longValue());

    }

    @Override
    public Float floatValue() {

        return new Float(b.floatValue());

    }

    @Override
    public Double doubleValue() {

        return new Double(b.doubleValue());

    }

    @Override
    public java.lang.Number getPrimitive() {

        return b;

    }


    @Override
    public final byte[] toBytes (ByteOrder order) {

        return ByteBuffer.allocate(Byte.BYTES).order(order).put(b).array();

    }

    @Override
    protected final void fromBytes(byte[] data, ByteOrder order) {

        this.b = ByteBuffer.wrap(data).order(order).get();

    }

    @Override
    protected final void fromBytes(byte[] bytes) {

        this.fromBytes(bytes, ByteOrder.nativeOrder());

    }

    @Override
    protected final byte[] toBytes() {

        return this.toBytes(ByteOrder.nativeOrder());

    }

    @Override
    public int length() {

        return Byte.BYTES;

    }

    @Override
    public String toString() {

        return b.toString();

    }

    @Override
    protected final int getTotalSpace() {

        return Byte.BYTES;

    }

    @Override
    public Class<Char> getCla$$() {

        return Char.class;

    }

    @Override
    public Char getData() {

        return this;

    }

    protected Number add(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.add(this);

        return new Char((byte) (b + num.getPrimitive().byteValue()));

    }

    @Override
    protected Number sub(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.sub(this).neg();

        return new Char((byte) (b - num.getPrimitive().byteValue()));

    }

    @Override
    protected Number molt(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.molt(this);

        return new Char((byte) (b * num.getPrimitive().byteValue()));

    }

    @Override
    protected Number div(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.div(this).opp();

        return new Double(b.floatValue() / num.getPrimitive().floatValue());

    }

    @Override
    protected Number neg() {

        return new Char((byte) -this.b);

    }

    @Override
    protected Number opp() {

        return new Double(1 / this.b);

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
