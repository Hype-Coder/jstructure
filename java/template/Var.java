package template;


import java.nio.ByteOrder;

// Questa classe è utile se utilizzata insieme alla classe Structure
public final class Var {


    private DataTypes.Data data;

    private String name;


    public Var ( String name, DataTypes.Data data ) throws InvalidArgument {

        if (name == null) throw new InvalidArgument("Invalid name passed as argument");

        this.name = name;

        this.data = data;

    }


    String getName () { return name; }

    Class<?> getCla$$() { return data.getCla$$(); }

    DataTypes.Data getValue () { return data; }

    void setValue (DataTypes.Data value) { this.data = value; }

    int length () { return data.length(); }

    int getDataSize () { return data.getTotalSpace(); }

    void fromBytes (byte[] bytes, ByteOrder byteOrder) throws InvalidArgument, InvalidStructure { data.fromBytes(bytes,byteOrder); }

    byte[] toBytes (ByteOrder byteOrder) { return data.toBytes(byteOrder); }

    public String toString () { return data.toString(); }

}
