package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.type.ColorRef;
import soopia.hwp.type.Dword;
import soopia.hwp.type.HwpByte;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.Int32;
import soopia.hwp.type.Int8;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.UInt8;
import soopia.hwp.type.record.BorderFillRecord;
import soopia.hwp.type.record.BorderFillRecord.BackgroundColorRef;
import soopia.hwp.type.record.BorderFillRecord.BackgroundImageRef;
import soopia.hwp.type.record.BorderFillRecord.GradationRef;
import soopia.hwp.type.record.BorderFillRecord.HwpBorder;
import soopia.hwp.util.IByteSource;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * 4.1.5 테두리/배경
 * 
 * @author chmin
 * @page p.18 (표-18)
 */
public class BorderFillRecordDecoder implements IDecoder<BorderFillRecord> {

	@Override
	public BorderFillRecord decode(BorderFillRecord record, IByteSource data,
			HwpContext context) throws DecodingException {
		// TEST created and not tested method stub
		
		int offset = record.getHeaderLength();
		data.skip(offset);
		UInt16 property = new UInt16(data.consume(2));//data, offset
		record.setBorderFillProperty(property);
		offset += property.getLength();
		offset += decodeBorders(record, data, context, offset);
		
		UInt32 type = new UInt32(data.consume(4));
		record.setFillType(type);
		offset += type.getLength();
		
		if ( record.isColorFilled() ){			
			offset += decodeColorFill(record, data,offset);
		}
		if ( record.isGradationFilled() ){
			offset += decodeGradationFill(record, data, offset);
		}
		if ( record.isImageFilled() ){
			offset += decodeImageFill(record, data, offset);
		}
		Dword size = new Dword(data.consume(4));
		record.setSizeInfo(size);
		offset += size.getLength();
		
		if ( size.getValue() == 1 ){
			// 그라데인션 설정되어있어야 하고 추가로 1비트를 읽어서 번짐정도의 중심값으로 설정.
			HwpByte centerBlur = new HwpByte(data.consume(1));
			record.getGradationRef().setGradationCenterBlur(centerBlur);
		}
		
		/* dummy data 용도를 알 수 없는 1~2바이트. 일단 저장해둔다.*/
		byte [] dummy = data.consume(data.remaining());//new byte [data.remaining()];
		record.setDummyBytes(dummy);
		return record;
	}
	
	private int decodeGradationFill(BorderFillRecord record,
			IByteSource data,
			final int offset ){
		int nRead = 0;
		Int8 grdntType = new Int8(data.consume(1));
		Int32 grdntTilt = new Int32(data.consume(4));
		Int32 grdntXPos = new Int32(data.consume(4));
		Int32 grdntYPos = new Int32(data.consume(4));
		Int32 grdntBlur = new Int32(data.consume(4));
		Int32 grdntColorCount = new Int32(data.consume(4));
		
		nRead += 5 * grdntBlur.getLength(); 
		ColorRef [] colors = new ColorRef[grdntColorCount.getValue()];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = new ColorRef(data.consume(4));
			nRead += colors[i].getLength();
		}
		record.setGradationRef(new GradationRef(
				grdntType, 
				grdntTilt, 
				grdntXPos, 
				grdntYPos, 
				grdntBlur, 
				colors));
		return nRead;
	}
	/*
	 * (표-27 그림 정보)
	 */
	private int decodeImageFill(BorderFillRecord record,
			IByteSource data,
			final int offset){
		int nRead = 0;
		HwpByte imageFillType = new HwpByte(data.consume(1));
		record.setImageFillType (imageFillType);
		nRead += imageFillType.getLength();
		
		Int8 brightness = new Int8(data.consume(1));
		Int8 contrast = new Int8(data.consume(1));
		HwpByte imageEffect = new HwpByte(data.consume(1));
		UInt16 imageId = new UInt16(data.consume(2));
		record.setBackgroundColorRef(new BackgroundImageRef(
				brightness, 
				contrast, 
				imageEffect, 
				imageId));
		nRead += imageId.getLength();
		return nRead;
	}
	/*
	 * (표-23 배경색, 무늬색, 무늬색 종류)
	 */
	private int decodeColorFill(BorderFillRecord record,
			IByteSource data,
			final int offset) {
		ColorRef bgColor = new ColorRef(data.consume(4));
		ColorRef patternColor = new ColorRef(data.consume(4));
		Int32 patternType = new Int32(data.consume(4));
		
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
	private int decodeBorders(BorderFillRecord record, IByteSource data,
			HwpContext context, final int offset ) {
		int nRead = 0;
		UInt8 borderType, borderSize;
		ColorRef color;
		HwpBorder [] border = new HwpBorder[5];
		for (int i = 0; i < border.length; i++) {
			borderType = new UInt8(data.consume(1));
			borderSize = new UInt8(data.consume(1));
			color = new ColorRef(data.consume(4));
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
		return "5.0.3.0".equals(versionString);
	}

}
