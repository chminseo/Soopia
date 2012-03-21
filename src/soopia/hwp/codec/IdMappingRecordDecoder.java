package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.record.IDMappingsRecord;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class IdMappingRecordDecoder implements IDecoder<IDMappingsRecord> {

	@Override
	public IDMappingsRecord decode(IDMappingsRecord record, ByteBuffer data,
			HwpContext context) throws DecodingException {
		
		data = record.getBuffer();
		int offset = record.getHeaderLength();
		record.setNumOfBinData(new UInt32(data, offset + 0));
		record.setNumOfKorFont(new UInt32(data, offset + 4));
		record.setNumOfEngFont(new UInt32(data, offset + 8));
		record.setNumOfChnFont(new UInt32(data, offset + 12));
		record.setNumOfJpnFont(new UInt32(data, offset + 16));
		record.setNumOfEtcFont(new UInt32(data, offset + 20));
		record.setNumOfSymbolFont(new UInt32(data, offset + 24));
		record.setNumOfUserFont(new UInt32(data, offset + 28));
		record.setNumOfBorder(new UInt32(data, offset + 32));
		record.setNumOfFontShape(new UInt32(data, offset + 36));
		record.setNumOfTab(new UInt32(data, offset + 40));
		record.setNumOfParaNum(new UInt32(data, offset + 44));
		record.setNumOfParaSymbol(new UInt32(data, offset + 48));
		record.setNumOfParaShape(new UInt32(data, offset + 52));
		record.setNumOfStyle(new UInt32(data, offset + 56));
		record.setNumOfMemoShape(new UInt32(data, offset + 60));
		
		return record;
	}

	@Override
	public boolean isAvailable(String versionString) {
		// TODO Auto-generated method stub
		return true;
	}

}
