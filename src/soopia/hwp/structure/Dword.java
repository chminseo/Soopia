package soopia.hwp.structure;

import java.nio.ByteBuffer;

import soopia.hwp.util.Converter;

/**
 * 16 bit 컴파일러에서 unsigned long 에 해당. (0~ 4294967295L) 
 * @author chmin
 *
 */
public class Dword extends AbstractType<Long> {

	public Dword(ByteBuffer src, int offset) {
		super(offset, 4, src);
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
	}

	@Override
	public Long getValue() {
		return Converter.getDword(this.getBytes(), 0);
	}

}
