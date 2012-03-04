package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * 부호없는 1 바이트 (0~255, p6 참고 )
 * @author yeori
 *
 */
public class HwpByte extends PrimitiveType<Integer> {
	/**
	 * 주어진 버퍼 src 에서 offset 위치에서 HwpByte 인스턴스를 생성한다.( 0~255 )
	 * (부호없는 1 바이트, 
	 * @param offset
	 * @param src
	 */
	public HwpByte(ByteBuffer src, int offset) {
		super(offset, 1, src);
	}
	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_BYTE;
	}	
	@Override
	protected void checkValid() {
		src.rewind();
		int val = src.get();
		if ( val < 0 || val >= 256 ){
			throw new IllegalArgumentException(" value should be between 0 and 255, but " + val);
		}

	}

	@Override
	public Integer getValue() {
		return (int) this.getBytes()[0];
	}
	
}
