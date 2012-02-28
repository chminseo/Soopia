package soopia.hwp.structure;

import java.nio.ByteBuffer;

import soopia.hwp.util.Converter;
/**
 *  16 비트 컴파일러에서 unsigned int 에 해당(0~65535, p6) 
 *  
 * @author yeori
 *
 */
public class Word extends AbstractType<Integer> {

	public Word (int offset, ByteBuffer src ){
		super(offset, 2, src);
		
	}
	@Override
	protected void checkValid() {
		;
	}

	@Override
	public String getStrucureName() {
		return Constant.STRUCTNAME_HWP_WORD;
	}

	@Override
	public byte[] getBytes() {
		byte [] b = new byte[2];
		src.rewind();
		src.get(b);
		return b;
	}
	


	@Override
	public Integer getValue() {
		return Converter.getWord(this.getBytes(), 0);
	}
	
	
}
