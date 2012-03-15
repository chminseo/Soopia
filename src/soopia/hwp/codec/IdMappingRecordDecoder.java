package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.record.IDMappingsRecord;

public class IdMappingRecordDecoder implements IDecoder<IDMappingsRecord> {

	@Override
	public IDMappingsRecord decode(IDMappingsRecord record, ByteBuffer data,
			HwpContext context) throws DecodingException {
		
		record.setNumOfBinData(new UInt32(data, 0));
		record.setNumOfKorFont(new UInt32(data, 4));
		record.setNumOfEngFont(new UInt32(data, 8));
		record.setNumOfChnFont(new UInt32(data, 12));
		record.setNumOfJpnFont(new UInt32(data, 16));
		record.setNumOfEtcFont(new UInt32(data, 20));
		record.setNumOfSymbolFont(new UInt32(data, 24));
		record.setNumOfUserFont(new UInt32(data, 28));
		record.setNumOfBorder(new UInt32(data, 32));
		record.setNumOfFontShape(new UInt32(data, 36));
		record.setNumOfTab(new UInt32(data, 40));
		record.setNumOfParaNum(new UInt32(data, 44));
		record.setNumOfParaSymbol(new UInt32(data, 48));
		record.setNumOfParaShape(new UInt32(data, 52));
		record.setNumOfStyle(new UInt32(data, 56));
		record.setNumOfMemoShape(new UInt32(data, 60));
		
		return record;
	}

	@Override
	public boolean isAvailable(String versionString) {
		// TODO Auto-generated method stub
		return true;
	}

}
