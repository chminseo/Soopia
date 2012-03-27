package soopia.hwp.codec;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.record.DocPropertyRecord;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
public class DocPropRecordDecoder extends AbstractRecordDecoder<DocPropertyRecord> {

	@Override 
	public boolean isAvailable(String versionString) {
		return true;
	};
	
	@Override
	public DocPropertyRecord decode(DocPropertyRecord target, IByteSource data,
			HwpContext context) throws DecodingException {
		super.decode(target, data, context); // 바이트 배열 복사 저장
		
		int pos = target.getHeaderLength(); // skip header
		data.skip(pos);
		target.setNumberOfSection(new UInt16(data.consume(2)));
		target.setStartOfPageNum(new UInt16(data.consume(2)) );
		target.setStartNumOfFootNote(new UInt16(data.consume(2)) );
		target.setStartNumOfEndNote(new UInt16(data.consume(2)));
		target.setStartNumOfPicture(new UInt16(data.consume(2)));
		target.setStartNumOfTable(new UInt16(data.consume(2)));
		target.setStartNumOfExpression(new UInt16(data.consume(2)));
		
		target.setListID(new UInt32(data.consume(4)));
		target.setParaID(new UInt32(data.consume(4)));
		target.setCursorPosInPara(new UInt32(data.consume(4)));
		
		return target;
	}

	
}
