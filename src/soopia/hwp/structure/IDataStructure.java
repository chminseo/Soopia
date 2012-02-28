package soopia.hwp.structure;

import java.nio.ByteBuffer;

public interface IDataStructure {
	
	public String getFilePath ();
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