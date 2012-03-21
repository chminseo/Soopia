package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.type.ColorRef;
import soopia.hwp.type.Dword;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.Int32;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.UInt8;
import soopia.hwp.type.record.BorderFillRecord;
import soopia.hwp.type.record.BorderFillRecord.BackgroundColorRef;
import soopia.hwp.type.record.BorderFillRecord.HwpBorder;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class BorderFillRecordDecoder implements IDecoder<BorderFillRecord> {

	@Override
	public BorderFillRecord decode(BorderFillRecord record, ByteBuffer data,
			HwpContext context) throws DecodingException {
		// TEST created and not tested method stub
		
		int offset = record.getHeaderLength();
		UInt16 property = new UInt16(data, offset);
		record.setBorderFillProperty(property);
		offset += property.getLength();
		offset += decodeBorders(record, data, context, offset);
		
		UInt32 type = new UInt32(data, offset);
		record.setFillType(type);
		offset += type.getLength();
		
		if ( record.isColorFilled() ){			
			offset += decodeColorFill(record, data, context, offset);
		}
		if ( record.isImageFilled() ){
			// TODO �����ؾ���.
		}
		if ( record.isGradationFilled() ){
			// TODO �����ؾ���.
		}
		Dword size = new Dword(data, offset);
		record.setSizeInfo(size);
		offset += size.getLength();
		
		if ( size.getValue() == 1 ){
			// �׶��μ� �����Ǿ��־�� �ϰ� �߰��� 1��Ʈ�� �о ���������� �߽ɰ����� ����.
		}
		
		/* dummy data �뵵�� �� �� ���� 1~2����Ʈ. �ϴ� �����صд�.*/
		byte [] dummy = new byte [data.remaining()];
		data.get(dummy);
		record.setDummyBytes(dummy);
		return record;
	}
	
	private int decodeColorFill(BorderFillRecord record, ByteBuffer data,
			HwpContext context, int offset) {
		ColorRef bgColor = new ColorRef(data, offset);
		ColorRef patternColor = new ColorRef(data, offset + 4);
		Int32 patternType = new Int32(data, offset+ 8);
		
		record.setBackgroundColorRef(new BackgroundColorRef(bgColor, patternColor, patternType));
		return (int) (
				bgColor.getLength() + 
				patternColor.getLength() + 
				patternType.getLength()
		);
	}

	/*
	 * ���� ������ �� �Ʒ� and �밢��
	 * �밢���� 5.0.3.0 ������ ������ �ʴ� �� �ϴ�.
	 */
	private int decodeBorders(BorderFillRecord record, ByteBuffer data,
			HwpContext context, final int offset ) {
		int nRead = 0;
		UInt8 borderType, borderSize;
		ColorRef color;
		HwpBorder [] border = new HwpBorder[5];
		for (int i = 0; i < border.length; i++) {
			borderType = new UInt8(data, offset + nRead);
			borderSize = new UInt8(data, offset + nRead+1);
			color = new ColorRef(data, offset + nRead+2);
			border[i] = new HwpBorder(borderType, borderSize, color);
			nRead += borderType.getLength() + borderSize.getLength() + color.getLength();
		}
		record.setLeftBorder(border[0]);
		record.setRightBorder(border[1]);
		record.setTopBorder(border[2]);
		record.setBottomBorder(border[3]);
		// TODO �밢���� �ϴ� ������
		// border[4];
		return nRead;
	}
	
	@Override
	public boolean isAvailable(String versionString) {
		// TEST created and not tested method stub
		return true;
	}

}
