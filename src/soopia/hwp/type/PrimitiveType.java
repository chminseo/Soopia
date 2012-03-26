package soopia.hwp.type;

import java.nio.ByteBuffer;
import java.util.Arrays;

import soopia.hwp.util.IByteSource;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 * @param <T>
 */
public abstract class PrimitiveType<T> implements IDataType {
	
//	protected int offset ;
//	protected ByteBuffer src ;
	
	protected byte [] data ;
	protected PrimitiveType(byte [] data){
		this.data = data;
	}
//	protected PrimitiveType(ByteBuffer src, int length){
//		this(src.position(), length, src);
//	}
//	protected PrimitiveType(int offset, int length, ByteBuffer src){
////		this.offset = offset;
////		this.src = cloneBuffer(src, offset, length);
////		this.src.clear();
//		this.checkValid();
//	}
	
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
//		return this.src.capacity();
		return data.length;
	}

	@Override
	public int getOffset() {
		throw new RuntimeException("������ �޼ҵ�");
//		return this.offset;
	}

	@Override
	public ByteBuffer getBuffer() {
		throw new RuntimeException("������ �޼ҵ�");
//		return this.src.asReadOnlyBuffer();
	}
	
	@Override
	public byte[] getBytes() {
		byte [] b = new byte[(int)getLength()];
		System.arraycopy(data, 0, b, 0, b.length);
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
		result = prime * result + Arrays.hashCode(data);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrimitiveType<T> other = (PrimitiveType<T>) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		return true;
	}

	
}
