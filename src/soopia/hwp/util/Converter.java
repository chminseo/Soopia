package soopia.hwp.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.poi.util.LittleEndian;

/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class Converter {
	final static int SZ_UINT16 = 2;
	final static int SZ_WORD = 2;
	final static int SZ_DWORD = 4;
	final static int SZ_UINT32 = 4;
	final static int SZ_INT32 = 4;
	final static int SZ_HWPUNIT16 = 2;
	final static int SZ_HWPUNIT = 4;
	/**
	 *  0 <= offset <= (offset + nRead) <= capacity
	 *  
	 *  읽어올 바이트 배열 data[offset.. (offset +nRead) ] : offset부터 nRead 개를 읽어서 반환한다.
	 *  
	 *  If offset + nRead < capacity, 반환할 배열의 크기를 capacity로 초기화한 후 [offset.. (offset +nRead) ] 배열을 복사한다.
	 *  
	 *  If offset + nRead == capacity, data를 단순히 반환.
	 *   
	 * @param data
	 * @param offset data 배열에서 읽기를 시작할 위치
	 * @param nRead offset 부터 읽어들일 바이트의 개수
	 * @param capacity 반환될 byte의 length 
	 * @return length가 capacity인 바이트 배열
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
	/**
	 * data 배열에서 2바이트를 읽어서 UInt16을 나타내는 0보다 큰  4바이트 Integer를 반환
	 * @param data
	 * @param offset
	 * @return
	 */
	public static int getUInt16 ( byte [] data, int offset){
		return LittleEndian.getUShort(data, offset); 
	}
	/**
	 * data 배열에서 2바이트를 읽어서 Word를 나타내는 0보다 큰 4바이트 Integer 반환
	 * @param data
	 * @param offset
	 * @return
	 */
	public static int getWord(byte [] data, int offset ){
		return  LittleEndian.getUShort(data, offset);
	}
	/**
	 * data 배열에서 4바이트를 읽어서 Dword를 나타내는 0보다 큰 8바이트 Long 반환
	 * @param data
	 * @param offset
	 * @return
	 */
	public static long getDword (byte [] data, int offset){
		int val = LittleEndian.getInt(data, offset);
		return 0x00FFFFFFFFL & val ;
		// getUInt() 버그 있음
		// return LittleEndian.getUInt(data, offset);
	}
	/**
	 * data 배열에서 1바이트를 읽어서 0보다 큰 Integer 반환.
	 * @param data
	 * @param offset
	 * @return
	 */
	public static Integer getHwpByte(byte[] data, int offset) {
		// TEST 반환값을 HwpByte로 할 것인지, Integer로 할 것인지..
		return LittleEndian.getUnsignedByte(data, offset);
	}
	/**
	 * data 배열에서 1바이트를 읽어서 UInt8을 나타내는 0보다 큰 Integer 로 반환 
	 * @param data
	 * @param offset
	 * @return
	 */
	public static Integer getUInt8(byte[] data, int offset) {
		// TEST created and not tested method stub
		return LittleEndian.getUnsignedByte(data, offset);
	}
	/**
	 * data 배열에서 4바이트를 읽어서 Int32를 나타내는 4바이트 Integer 반환
	 * @param data
	 * @param offset
	 * @return
	 */
	public static int getInt32 (byte [] data, int offset ){
		byte [] b = checkBytes(data, offset, SZ_INT32, 4);
		return LittleEndian.getInt(b, offset);
	}
	/**
	 * data 배열에서 4바이트를 읽어서 UInt32를 나타내는 0보다 큰 8바이트 Long 반환.
	 * @param data
	 * @param offset
	 * @return
	 */
	public static long getUInt32(byte[] data, int offset) {
		byte [] b = checkBytes(data, offset, SZ_UINT32, 8);
		return LittleEndian.getLong(b, (b == data)? offset : 0);
	}
//	public static Dword getDword(ByteBuffer buffer, int offset) {
//		return new Dword(buffer, offset);
//	}
	/**
	 * data 배열에서 4 바이트를 읽어서 8바이트 Long을 반환.
	 * @param data
	 * @param offset
	 * @return
	 */
	public static Long getHwpUnit(byte[] data, int offset) {
		byte [] b = checkBytes(data, offset, SZ_HWPUNIT, 8);
		return LittleEndian.getLong(b, (b == data)? offset : 0);
	}
	/**
	 * data 배열에서 2바이트를 읽어서  4바이트 Integer 를 반환.
	 *  
	 * @param data
	 * @param offset
	 * @return
	 */
	public static Integer getHwpUnit16(byte [] data, int offset) {
		// TEST
		byte [] b = checkBytes(data, offset, SZ_HWPUNIT16, 4);
		return LittleEndian.getInt(b, (b == data)? offset : 0);
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
		// TEST 구현해야함.
		return 0;
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
	 * retieves integer from partial bits indicated by [from, from + length] inclusive
	 * <pre>
	 *    from = 3, length = 6
	 *     
	 *            L     F
	 *     01000111 01000111
	 *            1 01000    => 40
	 * </pre>
	 * @param val
	 * @param from
	 * @param length
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
		// TEST 쓰이지 않고 있음.
		byte [] b = new byte[length];
		data.position(offset);
		data.get(b);
		return new String(b, "UTF-16LE");
	}
	public static String getString(IByteSource data, int length)
			throws UnsupportedEncodingException{
		return new String(data.consume(length), "UTF-16LE");
	}
	public static String toHexString(byte b){
		return (b < 0x10 ? "0" : "" ) + Integer.toHexString(0x0ff & b).toUpperCase();
	}
}
