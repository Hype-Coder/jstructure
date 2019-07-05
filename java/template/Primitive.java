package template;



import java.nio.ByteOrder;


public abstract class Primitive extends DataTypes.Data implements DataTypes {

    protected static final int default_value = 0;


    protected Primitive() {}

    protected abstract void fromBytes(byte[] bytes, ByteOrder order) throws InvalidArgument;

    protected int getTotalSpace() { return Byte.BYTES; }

    protected abstract Class<? extends Primitive> getCla$$ ();

}
