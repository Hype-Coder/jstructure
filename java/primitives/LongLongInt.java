package primitives;

import template.DataTypes;
import template.InvalidArgument;
import template.Primitive;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class LongLongInt extends primitives.Number {

    private Long l = (long) Primitive.default_value;


    public LongLongInt(long l) {

        this.l = l;

    }

    public LongLongInt(Long l) {

        this.l = l;

    }

    public LongLongInt() {


    }

    public LongLongInt(byte[] a,ByteOrder order) throws InvalidArgument {

        this.fromBytes(a,order);

    }

    public LongLongInt(byte[] array) throws InvalidArgument {

        this.fromBytes(array);

    }

    @Override
    public Char charValue() {

        return new Char(l.byteValue());

    }

    @Override
    public ShortInt shortValue() {

        return new ShortInt(l.shortValue());

    }

    @Override
    public Int intValue() {

        return new Int(l.intValue());

    }

    @Override
    public LongLongInt longValue() {

        return new LongLongInt(l);

    }

    @Override
    public primitives.Float floatValue() {

        return new primitives.Float(l.floatValue());

    }

    @Override
    public primitives.Double doubleValue() {

        return new primitives.Double(l.doubleValue());

    }

    @Override
    public java.lang.Number getPrimitive() {

        return this.l;

    }

    @Override
    public final int length() {

        return java.lang.Double.BYTES;

    }

    @Override
    public String toString() {

        return l.toString();

    }

    @Override
    public final Class<LongLongInt> getCla$$() {

        return LongLongInt.class;

    }

    @Override
    public final byte[] toBytes(ByteOrder order) {

        return ByteBuffer.allocate(Long.BYTES).order(order).putLong(l).array();

    }

    @Override
    protected final int getTotalSpace() {

        return Long.BYTES;

    }

    @Override
    public LongLongInt getData() { return this; }

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

        if (data == null) this.l = (long) 0x0;  // qua c'è un errore, se passo un array di byte di dimensione < di 8, questo viene trasformato in 0 automaticamente

        else if (data.length > 8) throw new InvalidArgument("Byte array too large to cast to Long Long Int value");

        else {

            if (data.length < 8) data = fillUp(data);

            this.l = ByteBuffer.wrap(data).getLong();

        }

    }


    private byte[] fillUp (byte[] data) {

        byte[] temp = new byte[8];

        for (int i=0, j=data.length - temp.length; i<temp.length; i++, j++) temp[i] = i < temp.length - data.length ? 0x0 : data[j];

        return temp;

    }


    @Override
    public Number add(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.add(this);

        return new LongLongInt(l + num.getPrimitive().longValue());

    }

    @Override
    public Number sub(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.add(this).neg();

        return new LongLongInt(l - num.getPrimitive().longValue());

    }

    @Override
    public Number molt(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.molt(this);

        return new LongLongInt(l * num.getPrimitive().longValue());

    }

    @Override
    public Number div(Number num) {

        if (DataTypes.Data.len(num) > DataTypes.Data.len(this)) return num.div(this).opp();

        return new Double(this.l.doubleValue() / num.getPrimitive().doubleValue());

    }

    @Override
    public Number neg() {

        return new LongLongInt(-this.l);

    }

    @Override
    protected Number opp() {

        return new primitives.Double(1 / this.l);

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
