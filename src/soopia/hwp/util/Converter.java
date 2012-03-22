package soopia.hwp.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.poi.util.LittleEndian;

import soopia.hwp.type.Dword;
import soopia.hwp.type.UInt16;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class Converter {
	static int SZ_UINT16 = 2;
	static int SZ_WORD = 2;
	static int SZ_DWORD = 4;
	static int SZ_UINT32 = 4;
	static int SZ_INT32 = 4;
	static int SZ_HWPUNIT = 4;
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
	public static Integer getUInt8(byte[] data, int offset) {
		// TEST created and not tested method stub
		return LittleEndian.getUnsignedByte(data, offset);
	}
	public static int getInt32 (byte [] data, int offset ){
		byte [] b = checkBytes(data, offset, SZ_INT32, 4);
		return LittleEndian.getInt(b, offset);
	}
	public static long getUInt32(byte[] data, int offset) {
		byte [] b = checkBytes(data, offset, SZ_UINT32, 8);
		return LittleEndian.getLong(b, (b == data)? offset : 0);
	}
	public static Dword getDword(ByteBuffer buffer, int offset) {
		return new Dword(buffer, offset);
	}
	public static Long getHwpUnit(byte[] data, int offset) {
		// TEST created and not tested method stub
		byte [] b = checkBytes(data, offset, SZ_HWPUNIT, 8);
		return LittleEndian.getLong(b, (b == data)? offset : 0);
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
	/**
	 * retieves integer from partial bits indicated by [from, from + length]
	 * <pre>
	 *    from = 3, length = 6
	 *     
	 *            L     F
	 *     01000111 01000111
	 *            1 01000    => 40
	 * </pre>
	 * @param val
	 * @param from
	 * @param end
	 * @return
	 */
	public static int getBits(int val, int from, int length){
		int v = val << (32-from-length);
		v = v >>> (32-length);
		return v ;
	}
	/**
	 * read characters from byte buffer with UTF16-LE 
	 * @param data
	 * @param i
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getString(ByteBuffer data, int offset, int length) throws UnsupportedEncodingException {
		byte [] b = new byte[length];
		data.position(offset);
		data.get(b);
		return new String(b, "UTF-16LE");
	}
}
