package soopia.hwp.util;

import java.nio.ByteBuffer;

import org.apache.poi.util.LittleEndian;

import soopia.hwp.structure.Dword;

public class Converter {
	
	static byte [] checkBytes(byte [] data, int length){
		byte [] b = data;
		if ( data.length < length ){
			b = new byte[length];
			System.arraycopy(data, 0, b, 0, data.length);
		}
		return b;
	}
	public static int getUInt16 ( byte [] data, int offset){
		byte [] b = checkBytes(data, 4);
		return LittleEndian.getInt(b, offset);
	}
	public static int getWord(byte [] data, int offset ){
		byte [] b = checkBytes(data, 4);
		return  LittleEndian.getInt(b, offset);
	}
	
	public static long getDword (byte [] data, int offset){
		byte [] b = checkBytes(data, 8);
		return LittleEndian.getLong(b, offset);
	}

	public static Integer getHwpByte(byte[] data, int offset) {
		return LittleEndian.getInt(data, offset);
	}
	public static long getUInt32(byte[] data, int offset) {
		byte [] b = checkBytes(data, 8);
		return LittleEndian.getLong(b, offset);
	}
	public static Dword getDword(ByteBuffer buffer, int offset) {
		return new Dword(buffer, offset);
	}
}
