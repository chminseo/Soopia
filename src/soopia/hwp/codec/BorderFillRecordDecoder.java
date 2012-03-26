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
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * 4.1.5 �׵θ�/���
 * 
 * @author chmin
 * @page p.18 (ǥ-18)
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
			// �׶��μ� �����Ǿ��־�� �ϰ� �߰��� 1��Ʈ�� �о ���������� �߽ɰ����� ����.
			HwpByte centerBlur = new HwpByte(data.consume(1));
			record.getGradationRef().setGradationCenterBlur(centerBlur);
		}
		
		/* dummy data �뵵�� �� �� ���� 1~2����Ʈ. �ϴ� �����صд�.*/
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
	 * (ǥ-27 �׸� ����)
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
	 * (ǥ-23 ����, ���̻�, ���̻� ����)
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
	 * ���� ������ �� �Ʒ� and �밢��
	 * �밢���� 5.0.3.0 ������ ������ �ʴ� �� �ϴ�.
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
		// TODO �밢���� �ϴ� ������
		// border[4];
		return nRead;
	}
	
	@Override
	public boolean isAvailable(String versionString) {
		// TEST created and not tested method stub
		return "5.0.3.0".equals(versionString);
	}

}
