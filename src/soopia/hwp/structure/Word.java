package soopia.hwp.structure;

import java.nio.ByteBuffer;

import soopia.hwp.util.Converter;
/**
 *  16 ��Ʈ �����Ϸ����� unsigned int �� �ش�(0~65535, p6) 
 *  
 * @author chmin
 *
 */
public class Word extends AbstractType<Integer> {

	public Word (ByteBuffer src, int offset){
		super(offset, 2, src);
		
	}
	@Override
	protected void checkValid() {
		int val = this.getValue();
		if ( val < 0 || val > 0xffff  ){
			throw new IllegalArgumentException(" value should be between 0 and 65535, but " + val);
		}
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_WORD;
	}
	
	@Override
	public Integer getValue() {
		return Converter.getWord(this.getBytes(), 0);
	}
	
	
}
