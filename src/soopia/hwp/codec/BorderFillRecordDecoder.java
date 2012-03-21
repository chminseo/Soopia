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
			// TODO 구현해야함.
		}
		if ( record.isGradationFilled() ){
			// TODO 구현해야함.
		}
		Dword size = new Dword(data, offset);
		record.setSizeInfo(size);
		offset += size.getLength();
		
		if ( size.getValue() == 1 ){
			// 그라데인션 설정되어있어야 하고 추가로 1비트를 읽어서 번짐정도의 중심값으로 설정.
		}
		
		/* dummy data 용도를 알 수 없는 1~2바이트. 일단 저장해둔다.*/
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
	 * 왼쪽 오른쪽 위 아래 and 대각선
	 * 대각선은 5.0.3.0 에서는 사용되지 않는 듯 하다.
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
		// TODO 대각선은 일단 제외함
		// border[4];
		return nRead;
	}
	
	@Override
	public boolean isAvailable(String versionString) {
		// TEST created and not tested method stub
		return true;
	}

}
