package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 *  unsigned __int16 ( 0 to 65,535 )
 * @author chmin
 *
 */
public class UInt16 extends PrimitiveType<Integer> {

	public UInt16(ByteBuffer src, int offset) {
		super(offset, 2, src);
		checkValid();
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_UINT16 ;
	}

	@Override
	protected void checkValid() {
		Integer val = this.getValue();
		if ( val < 0 || val > 65535 ){
			throw new IllegalArgumentException(" UINT16 should be between 0 and 65535, but " + val);
		}
	}

	@Override
	public Integer getValue() {
		return Converter.getUInt16(this.getBytes(), 0);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if ( obj instanceof UInt16 ){
			return this.getValue().equals(((UInt16)obj).getValue());
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "UINT16[" + this.getValue() + "]";
	}
}