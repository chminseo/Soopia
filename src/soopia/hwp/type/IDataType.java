package soopia.hwp.type;

import java.nio.ByteBuffer;

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