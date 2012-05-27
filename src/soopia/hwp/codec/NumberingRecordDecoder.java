package soopia.hwp.codec;

import java.io.UnsupportedEncodingException;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.HwpUnit16;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.Word;
import soopia.hwp.type.record.NumberingRecord;
import soopia.hwp.util.Converter;
import soopia.hwp.util.IByteSource;

public class NumberingRecordDecoder extends
		AbstractRecordDecoder<NumberingRecord> {
	
	@Override
	public boolean isAvailable(String versionString) {
		// TEST created and not tested method stub
		return true;
	}
	
	@Override
	public NumberingRecord decode(NumberingRecord record, IByteSource data,
			HwpContext context) throws DecodingException {
		super.decode(record, data, context);
		
		int offset = record.getHeaderLength();
		data.skip(offset);
		try {
			for ( int i = 0 ; i < NumberingRecord.NUMBERING_CNT ; i ++ ) {
				UInt32 property = new UInt32( data.consume(4) );
				HwpUnit16 revisedWidth = new HwpUnit16(data.consume(2));
				HwpUnit16 distFromText = new HwpUnit16(data.consume(2));
				UInt32 fontId = new UInt32(data.consume(4));
				Word charLength = new Word(data.consume(2));
				String formStr = Converter.getString(data, 2*charLength.getValue().intValue());
				record.addNumberingInfo(property, revisedWidth, distFromText, fontId, formStr.toCharArray());
			}
			
			// 2 bytes starting num
			record.setStartingNumber(new UInt16(data.consume(2)));
			
			// staring num at Level 1 ~ 7, each of 4 bytes
			for( int i = NumberingRecord.LEVEL_1 ; i < NumberingRecord.NUMBERING_CNT ; i ++ ){
				record.getNumberingInfoAt(i).setStartingNumber(new UInt32(data.consume(4)));
			}
			
			return record;
		} catch (UnsupportedEncodingException e) {
			throw new DecodingException("fail to create string", e);
		}
		
	}

}
