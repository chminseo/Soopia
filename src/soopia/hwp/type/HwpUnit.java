/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 */
package soopia.hwp.type;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;

/**
 * @author chmin
 *
 */
public class HwpUnit extends PrimitiveType<Long> {

	public HwpUnit(byte [] data){
		super(data);
	}
	
	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_HWPUNIT;
	}

	@Override
	protected void checkValid() {
		long val = this.getValue();
		if ( val < 0 || val > 4294967295L ){
			throw new IllegalArgumentException("HwpUnit should be between 0 and 4294967295L, but " + val);
		}
		if ( data.length != 4 ){
			throw new IllegalArgumentException("HwpUnit should be 4 bytes length, but " + data.length );
		}
	}

	/* (non-Javadoc)
	 * @see soopia.hwp.type.PrimitiveType#getValue()
	 */
	@Override
	public Long getValue() {
		return Converter.getHwpUnit(this.getBytes(), 0);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("HwpUnit [" + getValue() + "HU");
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
