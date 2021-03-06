package soopia.hwp.type;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * HWP DATA TYPE UINT8 
 * unsigned __int8
 * 
 * @author chmin
 * @page p.6 (표 1)
 * @range 0 ~ 255
 *
 */
public class UInt8 extends PrimitiveType<Integer> {

	public UInt8(byte [] data){
		super(data);
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_UINT8;
	}

	@Override
	protected void checkValid() {
		int val = getValue();
		if ( val < 0 || val > 255 )
			throw new IllegalArgumentException("UINT8 should be between 0 and 255, but " + val );

	}

	@Override
	public Integer getValue() {
		return Converter.getUInt8(this.getBytes(), 0);
	}
	@Override
	public String toString(){
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
