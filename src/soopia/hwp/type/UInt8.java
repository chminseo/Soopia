package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * HWP DATA TYPE UINT8 
 * unsigned __int8
 * 
 * @author chmin
 * @page p.6 (ǥ 1)
 * @range 0 ~ 255
 *
 */
public class UInt8 extends PrimitiveType<Integer> {

	public UInt8(ByteBuffer src){
		this(src, src.position());
	}
	public UInt8(ByteBuffer src, int offset) {
		super(offset, 1, src);
		checkValid();
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_UINT8;
	}

	@Override
	protected void checkValid() {
		// TEST created and not tested method stub
		Integer val = this.getValue();
		if ( val < 0 || val > 255 )
			throw new IllegalArgumentException("UINT8 should be between 0 and 255, but " + val );

	}

	@Override
	public Integer getValue() {
		// TEST created and not tested method stub
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
