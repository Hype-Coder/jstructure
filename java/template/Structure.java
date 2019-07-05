package template;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;


public abstract class Structure extends DataTypes.Data {


    private LinkedHashMap <String, Var> structure = new LinkedHashMap <> () ;  // contains all structure variable



    protected Structure () throws InvalidArgument {

        Arrays.stream(setVar()).forEach(var -> {

            structure.put(var.getName(), var);

        });

    }


    protected abstract Var[] setVar () throws InvalidArgument;


    public void setVar (String varName, DataTypes.Data data) {

        this.structure.get(varName).setValue(data);

    }


    public DataTypes.Data getVar (String name) {

        return structure.get(name).getValue();

    }


    public boolean containsVar (String name) {

        return structure.containsKey(name) ;

    }


    public Class<?> getVarClass (String name) {

        return structure.get(name).getCla$$();

    }


    public int getVarSize (String name) {

        return structure.get(name).length();

    }


    // how many variables in structure
    public final int length () {

        return structure.size();

    }


    @Override
    protected final int getTotalSpace() {

        return this.getVariables() .stream() .filter(Objects::nonNull) .mapToInt(var->var.getValue().getTotalSpace()) .sum();

    }


    @Override
    public final Class<Structure> getCla$$() {

        return Structure.class;

    }


    private Collection <Var> getVariables () {

        return structure.values();

    }


    @Override
    protected void fromBytes(byte[] bytes) throws InvalidStructure, InvalidArgument {

        this.fromBytes(bytes, ByteOrder.nativeOrder());

    }


    @Override
    protected byte[] toBytes() {

        return this.toBytes(ByteOrder.nativeOrder());

    }


    @Override
    protected byte [] toBytes (ByteOrder byteOrder) {

        ByteBuffer buffer = ByteBuffer.allocate ( this.getTotalSpace() );

        buffer.order (byteOrder);

        this.getVariables() .stream() .filter(Objects::nonNull) .forEach(var-> buffer.put ( var.toBytes(byteOrder)));

        return buffer.array ();

    }


    @Override
    protected void fromBytes (byte [] oldStructure, ByteOrder byteOrder) throws InvalidStructure, InvalidArgument {

        ByteBuffer buffer = ByteBuffer.wrap (oldStructure);

        buffer.order(byteOrder);

        // Se le dimensioni dell'array sono uguali alle dimensioni della nuova struttura, altrimenti non riuscirei a riempire correttamente la nuova struttura
        if (oldStructure.length > this.getTotalSpace()) throw new InvalidStructure ( "Old structure too large to cast to new structure" );

        for (Var var : this.getVariables()) {  // itero su ogni variabile della struttura che si intende riempire

            if (!buffer.hasRemaining()) break;  // verifica che il buffer contenga ancora dei byte da smistare

            if (var == null) continue;  // verifica che la variabile in questione non sia stata dichiarata null

            // se i byte rimanenti del buffer non coprono l'intera dimensione della variabile, prendi solo la dimensione rimanente di byte del buffer
            int next_item_size = buffer.remaining() < var.getDataSize() ? buffer.remaining() : var.getDataSize();

            byte[] data = new byte[next_item_size];

            buffer.get(data);

            // richiamo il metodo fromBytes che inizializzerà ciascuna variabile interna della nuova struttura con l'array di byte corrispondente ad una porzione della vecchia struttura
            var.fromBytes(data,byteOrder);

        }

    }


    @Override
    public String toString () {

        ArrayList<String> l = new ArrayList<>();

        structure.forEach((name,var)-> { l.add ( String.format("%s:%s",name,var.getValue() != null ? var.toString() : "null")); });

        return String.format("{%s}", String.join(",", l));

    }


}
