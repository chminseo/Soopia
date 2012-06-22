package soopia.hwp.codec;

import java.io.UnsupportedEncodingException;

import soopia.hwp.type.HwpByte;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.record.StyleRecord;
import soopia.hwp.util.Converter;
import soopia.hwp.util.IByteSource;

public class StyleRecordDecoder extends AbstractRecordDecoder<StyleRecord> {

	@Override
	public boolean isAvailable(String versionString) {
		// TODO 나중에 처리하자!
		return true;
	}

	@Override
	public StyleRecord decode(StyleRecord record, IByteSource data,
			HwpContext context) throws DecodingException {
		// TEST created and not tested method stub
		super.decode(record, data, context);
		
		data.skip((int)record.getHeaderLength()); // skip 4-bytes header
		int length = 0;
		try {
			length = Converter.getWord(data.consume(2), 0);
			record.setStyleNameKr( Converter.getString(data, 2*length) );
			
			length = Converter.getWord(data.consume(2), 0);
			record.setStyleNameEn( Converter.getString(data, 2*length) );
		} catch (UnsupportedEncodingException e) {
			throw new DecodingException("fail to read string", e);
		}
		
		record.setProperty( new HwpByte(data.consume(1)) );
		record.setNextStyleId( new HwpByte(data.consume(1)) );
		data.consume(2); //Int16 unknown2Bytes = new Int16(data.consume(2));
		record.setLangId( new UInt16(data.consume(2)) );
		record.setParaShapeId( new UInt16(data.consume(2)) );
		record.setFontShapeId( new UInt16(data.consume(2)) );
		
		return record;
	}

}
