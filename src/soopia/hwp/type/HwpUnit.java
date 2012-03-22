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

	public HwpUnit(ByteBuffer src){
		this(src, src.position());
	}
	
	/**
	 * @param offset
	 * @param length
	 * @param src
	 */
	public HwpUnit(ByteBuffer src, int offset) {
		super(offset, 4, src);
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
	}

	/* (non-Javadoc)
	 * @see soopia.hwp.type.PrimitiveType#getValue()
	 */
	@Override
	public Long getValue() {
		// TEST created and not tested method stub
		return Converter.getHwpUnit(this.getBytes(), 0);
	}

}
