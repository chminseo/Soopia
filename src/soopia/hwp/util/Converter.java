package soopia.hwp.util;

import org.apache.poi.util.LittleEndian;

public class Converter {
	public static int getWord(byte [] data, int offset ){
		byte [] b = new byte[4];
		b[0] = data[0];
		b[1] = data[1];
		return  LittleEndian.getInt(b, offset);
	}

	public static Integer getHwpByte(byte[] data, int offset) {
		return LittleEndian.getInt(data, offset);
	}
}
