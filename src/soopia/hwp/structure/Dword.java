package soopia.hwp.structure;

import java.nio.ByteBuffer;


public class Dword extends AbstractType<Long> {

	protected Dword(ByteBuffer src, int offset) {
		super(offset, 4, src);
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_DWORD;
	}

	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void checkValid() {
		
	}

	@Override
	public Long getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
