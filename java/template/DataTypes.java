package template;


import java.nio.ByteOrder;

public interface DataTypes {


    abstract class Data {

        protected abstract int getTotalSpace();  // Ottieni la dimensione totale della variabile espressa in byte

        protected abstract void fromBytes(byte[] bytes, ByteOrder order) throws InvalidArgument, InvalidStructure; // Converti un array di byte nella variabile interna alla classe

        protected abstract void fromBytes(byte[] bytes) throws InvalidStructure, InvalidArgument;

        protected abstract byte[] toBytes();

        protected abstract byte [] toBytes(ByteOrder order);  // Converti la variabile in un array di byte

        protected abstract Class<?> getCla$$();  // Ottieni la classe della variabile interna alla classe

        protected abstract int length(); // Ottieni la dimensione della variabile


        public static int len(Data obj) { return obj.getTotalSpace(); }

        public static void cast(byte[] byteArray, Data value, ByteOrder order) throws InvalidArgument, InvalidStructure { value.fromBytes(byteArray,order); }

        public static void cast(Data toCast, Data value, ByteOrder order) throws InvalidArgument, InvalidStructure { value.fromBytes(toCast.toBytes(order), order); }

        public static void cast(Data toCast, ByteOrder fromOrder, Data value, ByteOrder toOrder) throws InvalidArgument, InvalidStructure { value.fromBytes(toCast.toBytes(fromOrder), toOrder);}

        public static void cast(Data toCast, Data value) throws InvalidArgument, InvalidStructure { value.fromBytes(toCast.toBytes()); }

        public static void cast(byte[] byteArray, Data value) throws InvalidArgument, InvalidStructure { value.fromBytes(byteArray); }

        public static byte[] toBytes(Data data, ByteOrder order) { return data.toBytes(order); }

        public static byte[] toBytes(Data data) { return data.toBytes(); }

    }

    Data getData();

}
