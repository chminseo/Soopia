package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;

public class Int8 extends PrimitiveType<Byte> {

	public Int8(byte b){
		super(new byte[]{b});
	}
	public Int8(byte [] data){
		super(data);
	}
//	public Int8(ByteBuffer src){
//		this(src, src.position());
//	}
//	public Int8(ByteBuffer src, int offset) {
//		super(offset, 1, src);
//	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_INT8;
	}

	@Override
	protected void checkValid() {
		Byte b = getValue();
		if ( b < Byte.MIN_VALUE || b > Byte.MAX_VALUE )
			throw new IllegalArgumentException(" INT8 should be between " 
					+ Byte.MIN_VALUE + " and " + Byte.MAX_VALUE + " ,but " + b);
	}

	@Override
	public Byte getValue() {
		return new Byte(this.getBytes()[0]);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Int8 [" + getValue());
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
