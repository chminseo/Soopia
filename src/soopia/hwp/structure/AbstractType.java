package soopia.hwp.structure;

import java.nio.ByteBuffer;

public abstract class AbstractType<T> implements IDataStructure {
	
	protected int offset ;
	protected ByteBuffer src ;
	protected AbstractType(int offset, int length, ByteBuffer src){
		this.offset = offset;
		this.src = ((ByteBuffer) src.position(offset).limit(offset+length))
				.slice()
				.asReadOnlyBuffer();
		this.checkValid();
	}
	
	abstract protected void checkValid ( );
	abstract public T getValue();

	@Override
	public String getFilePath() {
		throw new RuntimeException("not supported in primitive data type : " + this.getStrucureName() );
	}
	
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
	
}
