package soopia.hwp.codec;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.record.BinDataRecord;
import soopia.hwp.util.Converter;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * 
 * @author chmin
 *
 */
public class BinDataRecordDecoder implements IDecoder<BinDataRecord> {

	@Override
	public BinDataRecord decode(BinDataRecord record, ByteBuffer data,
			HwpContext context) throws DecodingException {
		
		data = record.getBuffer();
		int offset = record.getHeaderLength();
		UInt16 property = new UInt16(data, offset);
		/* 0~3 bits */
		int bitType = Converter.getBits(property.getValue().intValue(), 0, 4);
		/* 4~5 bits */
		int compresionType = Converter.getBits(property.getValue(), 4, 2);
		/* 6~7 bits */
		int accessType = Converter.getBits(property.getValue(), 6, 2);
		record.setDataType (bitType);
		record.setCompressPolicy(compresionType);
		record.setAccessState(accessType);
		
		try {
			if ( bitType == BinDataRecord.TYPE_STORAGE){
				UInt16 binId = new UInt16(data, offset+2);
				String binDataId = decimalString(binId.getValue().intValue());
				record.setBinDataId(binDataId);
				
				UInt16 len3 = new UInt16(data, offset+4);
				String extension = Converter.getString(data, offset+6, len3.getValue() * 2 );
				record.setExtension(extension);
			} else if ( bitType == BinDataRecord.TYPE_LINK) {
				UInt16 absLen2 = new UInt16(data, offset+2);
				String absPath = Converter.getString(data, offset+4, absLen2.getValue() * 2);
				record.setAbsolutePath(absPath);
				offset += 4 + absLen2.getValue() * 2;
				
				UInt16 relLen2 = new UInt16(data, offset);
				String relPath = Converter.getString(data, offset+2, relLen2.getValue() * 2);
				record.setRelativePath(relPath);
			}
		} catch (UnsupportedEncodingException e) {
			throw new DecodingException("unsupported encoding UTF-16LE", e);
		}
		
		return record;
	}

	private static String PATTERN = "0000";
	private String decimalString(int value){
		String val = String.valueOf(value);
		return PATTERN.substring(0, PATTERN.length() - val.length()) + val;
	}
	@Override
	public boolean isAvailable(String versionString) {
		// TEST not tested
		return true;
	}
}
