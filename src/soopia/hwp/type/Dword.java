package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;

/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * 16 bit 컴파일러에서 unsigned long 에 해당. (0~ 4294967295L) 
 * @author chmin
 *
 */
public class Dword extends PrimitiveType<Long> {
	
	public Dword(byte [] data){
		super(data);
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_DWORD;
	}

	@Override
	protected void checkValid() {
		Long val = this.getValue();
		if ( val < 0 || val > 4294967295L ){
			throw new IllegalArgumentException(" value should be between 0 and 4294967295, but " + val);
		}
		if ( this.data.length != 4 ){
			throw new IllegalArgumentException("Dword should be 2 bytes-length, but " + this.data.length );
		}
	}

	@Override
	public Long getValue() {
		return Converter.getDword(this.getBytes(), 0);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Dword [getValue()=" + getValue());
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
