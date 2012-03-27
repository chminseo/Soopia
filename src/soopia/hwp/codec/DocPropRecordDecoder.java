package soopia.hwp.codec;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.record.DocPropertyRecord;
import soopia.hwp.util.IByteSource;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
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
		super.decode(target, data, context); // ����Ʈ �迭 ���� ����
		
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
