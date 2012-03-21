package soopia.hwp.codec;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import soopia.hwp.Constant;
import soopia.hwp.type.Dword;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.util.Converter;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class ParaTextDecoder {
	
	public void decode(IRecordStructure irs) throws IllegalArgumentException{
		if ( ! irs.getStrucureName().equals(Constant.PARA_TEXT) ) {
			throw new IllegalArgumentException("ParaTextDecoder requires " 
					+ Constant.PARA_TEXT + " structure, but " 
					+ irs.getStrucureName());
		}
		ByteBuffer data = irs.getBuffer();
		data.clear();
		
		int header = data.getInt();
		byte [] sectionCtrl = new byte[16], colCtrl = new byte[16];
		data.get(sectionCtrl).get(colCtrl);
		
		byte [] b = new byte[2];
		char [] chs = new char[data.capacity()/2];
		int i = 0;
		while ( data.hasRemaining() ){
			data.get(b);
			chs[i++] = Converter.getWChar(b);
//			System.out.println("[" + Converter.getWChar(b) + "]");
		}
		System.out.println(new String(chs));
	}

	private boolean isInineControl(char ch) {
		int [] inline = new int []{4, 5,6,7,8,9, 19, 20};
		for( int ctrl : inline){
			if ( ctrl == ch ) return true;
		}
		return false; 
	}
}
