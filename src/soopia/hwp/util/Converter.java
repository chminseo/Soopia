package soopia.hwp.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.poi.util.LittleEndian;

/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
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
	 *  �о�� ����Ʈ �迭 data[offset.. (offset +nRead) ] : offset���� nRead ���� �о ��ȯ�Ѵ�.
	 *  
	 *  If offset + nRead < capacity, ��ȯ�� �迭�� ũ�⸦ capacity�� �ʱ�ȭ�� �� [offset.. (offset +nRead) ] �迭�� �����Ѵ�.
	 *  
	 *  If offset + nRead == capacity, data�� �ܼ��� ��ȯ.
	 *   
	 * @param data
	 * @param offset data �迭���� �б⸦ ������ ��ġ
	 * @param nRead offset ���� �о���� ����Ʈ�� ����
	 * @param capacity ��ȯ�� byte�� length 
	 * @return length�� capacity�� ����Ʈ �迭
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
		// getUInt() ���� ����
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
//	public static Dword getDword(ByteBuffer buffer, int offset) {
//		return new Dword(buffer, offset);
//	}
	/**
	 * data �迭���� 8 ����Ʈ�� �о 64-bit Long�� ��ȯ.
	 * @param data
	 * @param offset
	 * @return
	 */
	public static Long getHwpUnit(byte[] data, int offset) {
		byte [] b = checkBytes(data, offset, SZ_HWPUNIT, 8);
		return LittleEndian.getLong(b, (b == data)? offset : 0);
	}
	/**
	 * data �迭���� 4����Ʈ�� �о 32bit-Integer �� ��ȯ.
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
	 * �и�����(mm)�� HWPUNIT ���� ��ȯ�Ѵ�.
	 * 1 mm = 1inch / 25.4 mm = 7200HU / 25.4mm
	 * 
	 * 1HU = 25.4mm / 7200HU
	 * 
	 * @param mm
	 * @return
	 */
	public static int mm2HU (int mm){
		// TEST �����ؾ���.
		return 0;
	}
//	public static char getChar(ByteBuffer data) {
//		byte [] b = new byte[2];
//		return 'c';
//	}
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
