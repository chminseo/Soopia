package soopia.hwp.codec;

import java.io.UnsupportedEncodingException;

import soopia.hwp.type.HwpByte;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.Word;
import soopia.hwp.type.record.FaceNameRecord;
import soopia.hwp.util.Converter;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * 글꼴정보에 대한 DECODER 
 * @author chmin
 *
 */
public class FaceNameDecoder implements IDecoder<FaceNameRecord> {

	@Override
	public FaceNameRecord decode(FaceNameRecord fNameRecord, IByteSource data,
			HwpContext context) throws DecodingException {
		int offset = fNameRecord.getHeaderLength();
		data.skip(offset);
		HwpByte bit = new HwpByte(data.consume(1));
		offset += bit.getLength();
		fNameRecord.setFaceProperty(bit);
		
		try {
			Word length = new Word(data.consume(2));
			offset += length.getLength();
			
			String fontName = Converter.getString(data, length.getValue()*2);
			fNameRecord.setFontName(fontName);
			offset += 2* length.getValue();
			
			if ( fNameRecord.hasAlternateFont() ){
				// TEST created and not tested method stub
				// TODO 샘플 코드에서 아직 발견 못했음. 발견 시 구현하도록 함.
				throw new RuntimeException("alternate font infomation found : implementation required ");
			}
			if ( fNameRecord.hasFaceTypeInfo() ){
				// TEST 각각의 유형 정보가 의미하는 바를 현재는 알지 못하고 있음.
				byte [] typeInfo = data.consume(10);//new byte [ 10];
//				data.position(offset);
//				data.get(typeInfo);
				fNameRecord.setFontTypeInfo(typeInfo);
				offset += typeInfo.length;
			}
			
			if ( fNameRecord.hasDefaultFont()){
				length = new Word(data.consume(2));
				offset += length.getLength();
				fontName = Converter.getString(data, 2* length.getValue() );
				fNameRecord.setDefaultFontName(fontName);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fNameRecord;
	}

	@Override
	public boolean isAvailable(String versionString) {
		// TEST created and not tested method stub
		return true;
	}

}
