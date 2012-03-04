package soopia.hwp.util;

import java.nio.ByteBuffer;

import org.apache.poi.util.LittleEndian;

import soopia.hwp.type.Dword;

public class Converter {
	static int SZ_UINT16 = 2;
	static int SZ_WORD = 2;
	static int SZ_DWORD = 4;
	static int SZ_UINT32 = 4;
	/*
	 *  0 <= offset <= nRead <= capacity
	 *  
	 *  읽어올 바이트 배열 data[offset..nRead]
	 */
	static byte [] checkBytes(byte [] data, int offset, int nRead, int capacity){
		byte [] target = data;
		if ( data.length < offset + nRead ){
			throw new IndexOutOfBoundsException(
					"invalid index : data.length(" + data.length 
					+") < offset(" + offset 
					+ ") + nRead (" + nRead + ")" );
		}
		if ( nRead < capacity ){
			target = new byte[capacity];
			System.arraycopy(data, offset, target, 0, nRead);
		}
		return target;
	}
	public static int getUInt16 ( byte [] data, int offset){
		return LittleEndian.getUShort(data, offset); 
	}
	public static int getWord(byte [] data, int offset ){
		return  LittleEndian.getUShort(data, offset);
	}
	
	public static long getDword (byte [] data, int offset){
		int val = LittleEndian.getInt(data, offset);
		return 0x00FFFFFFFFL & val ;
		// getUInt() 버그 있음
		// return LittleEndian.getUInt(data, offset);
	}

	public static Integer getHwpByte(byte[] data, int offset) {
		return LittleEndian.getUnsignedByte(data, offset);
	}
	public static long getUInt32(byte[] data, int offset) {
		byte [] b = checkBytes(data, offset, SZ_UINT32, 8);
		return LittleEndian.getLong(b, (b == data)? offset : 0);
	}
	public static Dword getDword(ByteBuffer buffer, int offset) {
		return new Dword(buffer, offset);
	}
	/**
	 * 밀리미터(mm)를 HWPUNIT 으로 변환한다.
	 * 1 mm = 1inch / 25.4 mm = 7200HU / 25.4mm
	 * 
	 * 1HU = 25.4mm / 7200HU
	 * 
	 * @param mm
	 * @return
	 */
	public static int mm2HU (int mm){
		// TODO 구현해야함.
		return 0;
	}
	public static char getChar(ByteBuffer data) {
		byte [] b = new byte[2];
		return 'c';
	}
	public static char getWChar(byte[] b) {
		int val = LittleEndian.getShort(b);
		return (char) val;
		
	}
	public static int getInt(ByteBuffer data) {
		byte [] b = new byte[4];
		data.get(b, 0, b.length);
		return LittleEndian.getInt(b);
	}
}
