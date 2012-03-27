package soopia.hwp.codec;


import soopia.hwp.type.HwpContext;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.record.IDMappingsRecord;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class IdMappingRecordDecoder extends AbstractRecordDecoder<IDMappingsRecord> {

	@Override
	public IDMappingsRecord decode(IDMappingsRecord record, IByteSource data,
			HwpContext context) throws DecodingException {
		super.decode(record, data, context); // 바이트 배열 복사 저장
		
//		data = record.getBuffer();
		int offset = record.getHeaderLength();
		data.skip(offset);
		record.setNumOfBinData(new UInt32(data.consume(4)));
		record.setNumOfKorFont(new UInt32(data.consume(4)));
		record.setNumOfEngFont(new UInt32(data.consume(4)));
		record.setNumOfChnFont(new UInt32(data.consume(4)));
		record.setNumOfJpnFont(new UInt32(data.consume(4)));
		record.setNumOfEtcFont(new UInt32(data.consume(4)));
		record.setNumOfSymbolFont(new UInt32(data.consume(4)));
		record.setNumOfUserFont(new UInt32(data.consume(4)));
		record.setNumOfBorder(new UInt32(data.consume(4)));
		record.setNumOfFontShape(new UInt32(data.consume(4)));
		record.setNumOfTab(new UInt32(data.consume(4)));
		record.setNumOfParaNum(new UInt32(data.consume(4)));
		record.setNumOfParaSymbol(new UInt32(data.consume(4)));
		record.setNumOfParaShape(new UInt32(data.consume(4)));
		record.setNumOfStyle(new UInt32(data.consume(4)));
		record.setNumOfMemoShape(new UInt32(data.consume(4)));
		
		return record;
	}

	@Override
	public boolean isAvailable(String versionString) {
		// TODO Auto-generated method stub
		return true;
	}

}
