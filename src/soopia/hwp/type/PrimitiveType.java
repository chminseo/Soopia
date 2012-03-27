package soopia.hwp.type;

import java.util.Arrays;

/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
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
		return data.length;
	}

	@Override
	public int getOffset() {
		throw new RuntimeException("������ �޼ҵ�");
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
	
}
