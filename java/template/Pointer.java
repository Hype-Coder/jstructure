package template;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Pointer extends DataTypes.Data implements DataTypes, AutoCloseable {

	
	static { System.load("/home/kali/IntelliJProjects/datastructures/jni/libdatastructure.so"); }

	
	private Long pointer;
	
	private int size;
	
	private boolean closed = false;


		
	public Pointer ( int size ) { pointer = native_malloc (size); this.size = size; }

	public Pointer ( int n, int size ) { pointer = native_calloc (n,size); this.size = size; }

	public Pointer ( long pointer, int size ) { pointer = native_realloc (pointer,size); this.size = size; }

	
	@Override
	public final int length() { return getpointersize(); }

	@Override
	public String toString() { return pointer.toString(); }

	@Override
	public Class<Pointer> getCla$$() { return Pointer.class; }

	public long getPointerValue() { return pointer; }
	
	@Override
	protected final byte[] toBytes(ByteOrder order) { return ByteBuffer.allocate(Long.BYTES).order(order).putLong(pointer).array(); }
	
	@Override
	protected final void fromBytes(byte[] data, ByteOrder order) { this.pointer = ByteBuffer.wrap(data).order(order).getLong(); }

	@Override
	protected void fromBytes(byte[] bytes) throws InvalidStructure, InvalidArgument { this.fromBytes(bytes, ByteOrder.nativeOrder()); }

	@Override
	protected byte[] toBytes() { return this.toBytes(ByteOrder.nativeOrder()); }

	@Override
	protected final int getTotalSpace() { return getpointersize(); }

	@Override
	public DataTypes.Data getData() { return this; }

	public final byte[] getValue () { 
		
		if (closed) throw new NullPointerException ("Pointer closed");
		
		return get (pointer,size); 
		
	}

	public final void setValue (DataTypes.Data data, ByteOrder order) throws InvalidArgument {

		if (closed) throw new NullPointerException ("Pointer closed");

		if (data.getTotalSpace() > size) throw new InvalidArgument ("Size too large for pointer");

		set (pointer, data.toBytes(order), size);

	}


	public final void setValue (DataTypes.Data data) throws InvalidArgument {

		if (closed) throw new NullPointerException ("Pointer closed");

		if (data.getTotalSpace() > size) throw new InvalidArgument ("Size too large for pointer");

		set (pointer, data.toBytes(ByteOrder.nativeOrder()), size);

	}
	
	@Override
	public final void close() throws Exception {

		if (closed) throw new NullPointerException("Pointer closed");
				
		free (pointer);
		
		closed = true;
		
	}
	
	
	private native int getpointersize ();
	
	private native long native_malloc (int size);
	
	private native long native_calloc (int n, int size);
	
	private native long native_realloc (long ptr, int size);
	
	private native int free (long prt);
	
	private native int sizeof (long ptr);
	
	private native byte[] get (long ptr, int size);
	
	private native void set (long ptr, byte[] bytes, int size);



}
