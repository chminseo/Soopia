package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * 색상값을 나타내는 32bit 정수를 표현.<br/>
 * 정수값은 0xBBGGRR의 형태.
 * @author chmin
 * @page p.6 (표-1)
 *
 */
public class ColorRef extends PrimitiveType<Integer> {

	public ColorRef(ByteBuffer src, int offset) {
		super(offset, 4, src);
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_COLORREF;
	}

	@Override
	protected void checkValid() {
		
	}

	@Override
	public Integer getValue() {
		return (int) Converter.getInt32(this.getBytes(), 0);
	}

	public Integer getRed() {
		return new Integer ( this.getValue() & 0xFF );
	}
	
	public Integer getGreen() {
		return new Integer ( (this.getValue()>>8) & 0xFF );
	}
	
	public Integer getBlue() {
		return new Integer ( (this.getValue()>>16) & 0xFF );
	}

	static String PATTERN = "00";
	public String toWebColorString() {
		String v ;
		StringBuilder sb = new StringBuilder("#");
		
		v = Integer.toHexString(getRed());
		sb.append(PATTERN.substring(v.length()) + v);
		v = Integer.toHexString(getGreen());
		sb.append(PATTERN.substring(v.length()) + v);
		v = Integer.toHexString(getBlue());
		sb.append(PATTERN.substring(v.length()) + v);
		return sb.toString().toUpperCase();
	}
	@Override
	public String toString(){
		return "ColorRef[" + this.getValue() + ", RGB =" + this.toWebColorString()+ "]";
	}

}
