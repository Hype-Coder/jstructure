package template;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class List <S extends DataTypes.Data> extends DataTypes.Data {


    private ArrayList<S> list;
   

	public List ( S[] datatypes ) {
        
		list = new ArrayList<>();

        list.addAll(Arrays.asList(datatypes));

    }


    public void put (S value) { this.list.add (value); }

    public void put (int pos, S value) { this.list.add (pos,value); }

    public S get (int pos) { return this.list.get(pos); }

    @Override
    public Class<?> getCla$$() { return list.getClass(); }

    @Override
    public int length() { return list.size(); }

    @Override
    public int getTotalSpace() { return list.stream().filter(Objects::nonNull).mapToInt(DataTypes.Data::getTotalSpace).sum(); }

    @Override
    protected void fromBytes(byte[] bytes) throws InvalidStructure, InvalidArgument { this.fromBytes(bytes, ByteOrder.nativeOrder()); }

    @Override
    protected byte[] toBytes() { return this.toBytes(ByteOrder.nativeOrder()); }

    @Override
    public byte[] toBytes(ByteOrder byteOrder) {

        ByteBuffer buffer = ByteBuffer.allocate (getTotalSpace());

        list.stream().filter(Objects::nonNull)  .forEach(data->buffer.put(data.toBytes(byteOrder)));

        return buffer.array();

    }


    @Override
    public void fromBytes(byte[] bytes, ByteOrder byteOrder) throws InvalidArgument, InvalidStructure {

        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        for (int i = 0; i < list.size(); i++) {

            if ( list.get(i) != null ) {

                byte[] data = new byte[list.get(i).getTotalSpace()];

                buffer.get(data);

                list.get(i).fromBytes(data, byteOrder);

            }

        }

    }


    @Override
    public String toString () {

        ArrayList<String> l = new ArrayList<>();

        list.stream().filter(Objects::nonNull) .forEach (var-> l.add(var.toString()));

        return String.format("[%s]", String.join(",", l));

    }


}
