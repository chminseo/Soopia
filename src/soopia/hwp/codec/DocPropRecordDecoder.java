package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.record.DocPropertyRecord;
import soopia.hwp.util.Converter;

public class DocPropRecordDecoder implements IDecoder<DocPropertyRecord> {

	@Override 
	public boolean isAvailable(String versionString) {
		return true;
	};
	
	@Override
	public DocPropertyRecord decode(DocPropertyRecord target, ByteBuffer datas,
			HwpContext context) throws DecodingException {
		
		ByteBuffer data = target.getBuffer();
		int pos = target.getHeaderLength(); // skip header
		target.setNumberOfSection(new UInt16(data, pos + 0));
		target.setStartOfPageNum(new UInt16(data, pos + 2));
		target.setStartNumOfFootNote(new UInt16(data, pos + 4));
		target.setStartNumOfEndNote(new UInt16(data, pos + 6));
		target.setStartNumOfPicture(new UInt16(data, pos + 8));
		target.setStartNumOfTable(new UInt16(data, pos + 10));
		target.setStartNumOfExpression(new UInt16(data, pos + 12));
		
		target.setListID(new UInt32(data, pos + 14));
		target.setParaID(new UInt32(data, pos + 18));
		target.setCursorPosInPara(new UInt32(data, pos + 22));
		
		return target;
	}

	
}
