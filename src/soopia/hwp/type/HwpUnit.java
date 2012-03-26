/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 */
package soopia.hwp.type;

import java.nio.ByteBuffer;

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
		sb.append("HwpUnit [" + getValue());
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
