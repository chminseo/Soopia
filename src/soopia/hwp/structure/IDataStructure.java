package soopia.hwp.structure;

public interface IDataStructure {
	/**
	 * returns the number of bytes
	 * @return number of bytes
	 */
	public long getLength();
	
	public byte [] getBytes();
}