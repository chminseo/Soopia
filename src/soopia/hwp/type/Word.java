package soopia.hwp.type;

import java.nio.ByteBuffer;

import soopia.hwp.Constant;
import soopia.hwp.util.Converter;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 *  16 비트 컴파일러에서 unsigned int 에 해당(0~65535, p6) 
 *  
 * @author chmin
 *
 */
public class Word extends PrimitiveType<Integer> {

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
	
	@Override
	public String toString(){
		return "WORD[val=" + this.getValue() + "]";
	}
}
