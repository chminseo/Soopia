package soopia.hwp.codec;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.HwpUnit;
import soopia.hwp.type.HwpUnit16;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.record.ParaShapeRecord;
import soopia.hwp.util.IByteSource;

public class ParaShapeRecordDecoder extends AbstractRecordDecoder<ParaShapeRecord> {

	@Override
	public boolean isAvailable(String versionString) {
		return true;
	}

	@Override
	public ParaShapeRecord decode(ParaShapeRecord record, IByteSource data,
			HwpContext context) throws DecodingException {
		super.decode(record, data, context);
		
		int offset = record.getHeaderLength();
		data.skip(offset);
		
		record.setProperty(new UInt32(data.consume(4)));
		record.setPaddingLeft(new HwpUnit(data.consume(4)));
		record.setPaddingRight(new HwpUnit(data.consume(4)));
		record.setIndentation(new HwpUnit(data.consume(4)));
		record.setPaddingTop(new HwpUnit(data.consume(4)));
		record.setPaddingBottom(new HwpUnit(data.consume(4)));
		
		record.setLineHeight(new HwpUnit(data.consume(4)));
		
		record.setTabDefId(new UInt16(data.consume(2)));
		record.setNumberingBulletId(new UInt16(data.consume(2)));
		record.setBorderFillId(new UInt16(data.consume(2)));
		
		record.setBorderMarginLeft(new HwpUnit16(data.consume(2)));
		record.setBorderMarginRight(new HwpUnit16(data.consume(2)));
		record.setBorderMarginTop(new HwpUnit16(data.consume(2)));
		record.setBorderMarginBottom(new HwpUnit16(data.consume(2)));
		
		record.setProperty2(new UInt32(data.consume(4)));
		record.setPropertyForLineHeigh(new UInt32(data.consume(4)));
		
		return record;
	}
}
