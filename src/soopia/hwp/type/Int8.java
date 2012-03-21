package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;

public class Int8 extends PrimitiveType<Byte> {

	public Int8(ByteBuffer src, int offset) {
		super(offset, 1, src);
	}

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

}
