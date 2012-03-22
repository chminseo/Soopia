package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
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
		StringBuilder sb = new StringBuilder();
		sb.append("Int16 [" + getValue());
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
