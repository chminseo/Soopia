package soopia.hwp.util;

import org.apache.poi.util.LittleEndian;

public class Converter {
	
	public static long getDword (byte [] data, int offset){
		byte [] b = data;
		if ( data.length < 8 ){
			b = new byte[8];
			System.arraycopy(data, 0, b, 0, data.length);
		}
		return LittleEndian.getLong(b, offset);
	}
	
	public static int getWord(byte [] data, int offset ){
		byte [] b = data;
		
		if ( b.length < 4){
			b = new byte[4];
			System.arraycopy(data, 0, b, 0, data.length);
//			b[0] = data[0];
//			b[1] = data[1];
		}
		return  LittleEndian.getInt(b, offset);
	}

	public static Integer getHwpByte(byte[] data, int offset) {
		return LittleEndian.getInt(data, offset);
	}
}
