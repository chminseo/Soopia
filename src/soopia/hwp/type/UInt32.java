package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * unsigned __int32 ( 0 ~ 4,294,967,295 )
 * 
 * @author chmin
 *
 */
public class UInt32 extends PrimitiveType<Long> {

	public UInt32(byte [] data ){
		super(data);
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
		if ( this.data.length != 4 ){
			throw new IllegalArgumentException("Int32 should be 2 bytes-length, but " + this.data.length );
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
		StringBuilder sb = new StringBuilder();
		sb.append("UInt32 [" + getValue());
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
