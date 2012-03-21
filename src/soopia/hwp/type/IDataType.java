package soopia.hwp.type;

import java.nio.ByteBuffer;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public interface IDataType {
	
	public HwpContext getHwpContext();
	
	public String getStrucureName ();
	/**
	 * returns the number of bytes
	 * @return number of bytes
	 */
	public long getLength();
	
	public int getOffset();
	
	public ByteBuffer getBuffer();
	
	public byte [] getBytes();
	
}