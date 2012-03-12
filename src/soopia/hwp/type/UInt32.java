package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * unsigned __int32 ( 0 ~ 4,294,967,295 )
 * 
 * @author chmin
 *
 */
public class UInt32 extends PrimitiveType<Long> {

	public UInt32(ByteBuffer src, int offset) {
		super(offset, 4, src);
		checkValid();
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_UINT32;
	}

	@Override
	protected void checkValid() {
		long val = this.getValue();
		if ( val < 0 || val > 4294967295L ){
			throw new IllegalArgumentException(" UINT32 should be between 0 and 4294967295L, but " + val);
		}
	}

	@Override
	public Long getValue() {
		return Converter.getUInt32(this.getBytes(), 0);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if ( obj instanceof UInt32 ){
			return this.getValue().equals(((UInt16)obj).getValue());
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "UINT32[" + this.getValue() + "]";
	}
	

}
