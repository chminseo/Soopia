package soopia.hwp.type;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;

public class HwpUnit16 extends PrimitiveType<Integer> {

	public HwpUnit16(byte[] data) {
		super(data);
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_HWPUNIT16;
	}

	@Override
	protected void checkValid() {
		long val = this.getValue();
		if ( val < 0 || val > 65535 ){
			throw new IllegalArgumentException("HwpUnit16 should be between 0 and 65535, but " + val);
		}
		if ( data.length != 2 ){
			throw new IllegalArgumentException("HwpUnit16 should be 2 bytes length, but " + data.length );
		}
	}

	@Override
	public Integer getValue() {
		return Converter.getHwpUnit16(this.getBytes(), 0);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("HwpUnit16 [" + getValue() + "HU");
		sb.append(", bytes [");
		byte [] b = getBytes();
		for (int i = 0; i < b.length; i++) {
			sb.append(Integer.toHexString(0xFF&b[i]) + ", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append("]]");
		return sb.toString();
	}

}
