package template;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public abstract class Array extends DataTypes.Data implements DataTypes {


    private Data[] array;


    public Array ( Data[] datatypes ) {

        this.array = Arrays.copyOf(datatypes,datatypes.length);

    }


    public Array ( int ... nodes ) {

        int depth = 0, node = nodes[depth];

        this.array = createDatatypeArray(node);

        this.array = generateMultiDimensionArray(array, nodes, depth + 1, node);

    }


    // Genero un array di sottonodi
    protected abstract Data[] createDatatypeArray(int node);


    // Genero un array di foglie
    protected abstract Data[] generateBaseArray(int size);


    protected abstract Data newArray(Data[] multiDimensions);


    private Data[] generateMultiDimensionArray(Data[] array, int[] nodes, int depth, int arcs){

        if (depth == nodes.length) return generateBaseArray(nodes[depth - 1]);

        while (--arcs >= 0) {

            Data[] temp = createDatatypeArray(nodes[depth]);

            Data[] multi_dimension = generateMultiDimensionArray(temp, nodes, depth + 1, nodes[depth]);  // genero i nodi interni dell'array

            array[arcs] = newArray(multi_dimension);  // inizializzo ciascun componente dell'array !

        }

        return array;

    }


    public Data get (int ... nodes) {

        return get(this, 0, nodes);

    }


    private Data get(Data array, int depth, int ... nodes){

        int node = nodes[depth];

        Array temp = (Array) array;

        Data subNode = temp.array[node];

        if (!(depth == nodes.length - 1)) {

            if (subNode == null) throw new NullPointerException();

            else if (!Array.class.isAssignableFrom(subNode.getClass())) throw new IndexOutOfBoundsException("You are trying to get an element in a dimension greater than that of the array");

            else return get(subNode, depth + 1, nodes);

        }

        return subNode;

    }


    public void set(Data value, int... nodes) throws InvalidArgument {

        set(this, 0, value, nodes);

    }


    private void set(Data array, int depth, Data value, int ... nodes) throws InvalidArgument {

        int node = nodes[depth];

        Array temp = (Array) array;

        Data subNode = temp.get(node);

        if (!(depth == nodes.length - 1)) {  // se non ho raggiunto la profondita massima richiesta da nodes

            if (subNode == null) throw new NullPointerException("You are trying to insert in a null array");

            else if (!Array.class.isAssignableFrom(subNode.getClass())) throw new IndexOutOfBoundsException("You are trying to insert an element in a dimension greater than that of the array");

            else set(subNode, depth + 1, value, nodes);

        }

        else

            temp.array[node] = value;

    }


    public int length() {

        return array.length;

    }


    @Override
    public Class<?> getCla$$ () {

        return array.getClass();

    }


    @Override
    protected final int getTotalSpace() {

        return Arrays.stream(array).filter(Objects::nonNull).mapToInt(Data::getTotalSpace).sum();

    }


    @Override
    protected void fromBytes(byte[] bytes) throws InvalidStructure, InvalidArgument {

        this.fromBytes(bytes, ByteOrder.nativeOrder());

    }


    @Override
    protected byte[] toBytes() {

        return this.toBytes(ByteOrder.nativeOrder());

    }


    protected void fromBytes (byte[] values, ByteOrder byteOrder) throws InvalidArgument, InvalidStructure {

        ByteBuffer buffer = ByteBuffer.wrap(values);

        for (int i=0; i<length() && buffer.hasRemaining(); i++) {

            if (array[i] == null) continue;

            int next_item_size = buffer.remaining() < array[i].getTotalSpace() ? buffer.remaining() : array[i].getTotalSpace();

            byte[] data = new byte[next_item_size];  // crea un array temporaneo usato come buffer, della dimensione reale della variabile che si vuole convertire

            buffer.get(data);  // riempi il buffer

            array[i].fromBytes(data, byteOrder); // prendi l'oggetto in posizione i nell'array e richiama il suo metodo fromBytes (cosi da settare la sua variabile)

        }

    }


    @Override
    public Data getData() {

        return this;

    }


    @Override
    protected final byte[] toBytes(ByteOrder byteOrder) {

        ByteBuffer buffer = ByteBuffer.allocate (getTotalSpace());

        Arrays.stream(array).filter(Objects::nonNull).forEach(data->buffer.put(data.toBytes(byteOrder)));

        return buffer.array();

    }


    @Override
    public String toString () {

        ArrayList<String> l = new ArrayList<>();

        Arrays.stream (array) .filter(Objects::nonNull) .forEach (var-> l.add(var.toString()));  // stampa Array richiamando il metodo toString di ciascun oggetto contenuto nell'array (variabile) 

        return String.format("[%s]", String.join(",", l));

    }

}
