package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * ��ȣ���� 1 ����Ʈ (0~255, p6 ���� )
 * @author yeori
 *
 */
public class HwpByte extends PrimitiveType<Integer> {
	public HwpByte(byte [] data){
		super(data);
	}
	/**
	 * �־��� ���� src ���� offset ��ġ���� HwpByte �ν��Ͻ��� �����Ѵ�.( 0~255 )
	 * (��ȣ���� 1 ����Ʈ, 
	 * @param offset
	 * @param src
	 */
//	public HwpByte(ByteBuffer src, int offset) {
//		super(offset, 1, src);
//	}
	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_BYTE;
	}	
	@Override
	protected void checkValid() {
//		src.rewind();
		int val = getValue();
		if ( val < 0 || val >= 256 ){
			throw new IllegalArgumentException(" value should be between 0 and 255, but " + val);
		}

	}

	@Override
	public Integer getValue() {
		return (int) this.getBytes()[0];
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("HwpByte [" + getValue());
		sb.append(", bytes [");
		sb.append(Integer.toHexString(0xFF&getBytes()[0]));
		sb.append("]]");
		return sb.toString();
	}
	
}
