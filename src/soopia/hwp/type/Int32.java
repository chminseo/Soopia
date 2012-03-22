package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * signed __int32 
 * 
 * @author chmin
 * @page p.6 (표-1)
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Int32 [" + getValue());
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
