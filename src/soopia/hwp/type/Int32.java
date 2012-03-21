package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * signed __int32 
 * 
 * @author chmin
 * @page p.6 (ǥ-1)
 */
public class Int32 extends PrimitiveType<Integer> {

	public Int32(ByteBuffer src, int offset) {
		super(offset, 4, src);
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_INT32;
	}

	@Override
	protected void checkValid() {
		Integer val = this.getValue();
		if ( val < new Long(Integer.MIN_VALUE) || val > new Long(Integer.MAX_VALUE)){
			throw new IllegalArgumentException(
					" INT32 should be between " 
						+ Integer.MIN_VALUE + " and " + Integer.MAX_VALUE + " ,but " + val
			);
		}
	}

	@Override
	public Integer getValue() {
		return Converter.getInt32(this.getBytes(), 0);
	}

}
