package soopia.hwp.codec;

import java.io.UnsupportedEncodingException;

import soopia.hwp.type.HwpByte;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.Word;
import soopia.hwp.type.record.FaceNameRecord;
import soopia.hwp.util.Converter;
import soopia.hwp.util.IByteSource;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * �۲������� ���� DECODER 
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
				// TODO ���� �ڵ忡�� ���� �߰� ������. �߰� �� �����ϵ��� ��.
				throw new RuntimeException("alternate font infomation found : implementation required ");
			}
			if ( fNameRecord.hasFaceTypeInfo() ){
				// TEST ������ ���� ������ �ǹ��ϴ� �ٸ� ����� ���� ���ϰ� ����.
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
