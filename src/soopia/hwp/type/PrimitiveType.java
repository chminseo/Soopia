package soopia.hwp.type;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 * @param <T>
 */
public abstract class PrimitiveType<T> implements IDataType {
	
	protected byte [] data ;
	protected PrimitiveType(byte [] data){
		this.data = data;
		checkValid();
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
		throw new RuntimeException("없어질 메소드");
//		return this.offset;
	}

	@Override
	public ByteBuffer getBuffer() {
		throw new RuntimeException("없어질 메소드");
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
