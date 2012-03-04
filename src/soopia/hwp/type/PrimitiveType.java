package soopia.hwp.type;

import java.nio.ByteBuffer;

public abstract class PrimitiveType<T> implements IDataType {
	
	protected int offset ;
	protected ByteBuffer src ;
	protected PrimitiveType(int offset, int length, ByteBuffer src){
		this.offset = offset;
		this.src = ((ByteBuffer) src
				.limit(offset+length)
				.position(offset)
			)
			.slice()
			.asReadOnlyBuffer();
		this.checkValid();
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
		return this.src;
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
	
}
