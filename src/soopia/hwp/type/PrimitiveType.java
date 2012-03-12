package soopia.hwp.type;

import java.nio.ByteBuffer;

public abstract class PrimitiveType<T> implements IDataType {
	
	protected int offset ;
	protected ByteBuffer src ;
	protected PrimitiveType(int offset, int length, ByteBuffer src){
		this.offset = offset;
		this.src = cloneBuffer(src, offset, length);
		this.src.clear();
		this.checkValid();
	}
	
	private ByteBuffer cloneBuffer(ByteBuffer src, int offset, int length){
		byte [] b = new byte [length];
		src.position(offset);
		src.get(b);
		return ByteBuffer.wrap(b);
	}
	
	abstract protected void checkValid ( );
	abstract public T getValue();
	
	@Override
	public long getLength() {
		return this.src.capacity();
	}

	@Override
	public int getOffset() {
		return this.offset;
	}

	@Override
	public ByteBuffer getBuffer() {
		return this.src.asReadOnlyBuffer();
	}
	
	@Override
	public byte[] getBytes() {
		byte [] b = new byte[(int)getLength()];
		src.rewind();
		src.get(b);
		return b;
	}
	
	@Override
	public HwpContext getHwpContext() {
		throw new UnsupportedOperationException("primitive data type implementation");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + offset;
		result = prime * result + ((src == null) ? 0 : src.hashCode());
		return result;
	}
}
