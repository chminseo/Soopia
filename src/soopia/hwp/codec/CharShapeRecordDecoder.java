/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 */
package soopia.hwp.codec;

import java.nio.ByteBuffer;

import soopia.hwp.type.ColorRef;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.HwpUnit;
import soopia.hwp.type.Int32;
import soopia.hwp.type.Int8;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.UInt8;
import soopia.hwp.type.Word;
import soopia.hwp.type.record.CharShapeRecord;

/**
 * @author chmin
 *
 */
public class CharShapeRecordDecoder implements IDecoder<CharShapeRecord> {

	/**
	 * 
	 */
	public CharShapeRecordDecoder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CharShapeRecord decode(CharShapeRecord record, ByteBuffer data,
			HwpContext context) throws DecodingException {
		// TEST created and not tested method stub
		int offset = record.getHeaderLength();
		data.position(offset);
		Word [] fontIds = decodeFontIds(data, 7);
		offset += fontIds.length * fontIds[0].getLength();
		UInt8 [] charWidthRatio = readUInt8s(data, 7); // ���� ( 50% ~ 200% )
		Int8 [] charGapRatio = readInt8s(data, 7); // �ڰ� (-50% ~ 50% )
		UInt8 [] charSizeRatio = readUInt8s(data, 7); // ������� ���� ũ��(10% ~ 250%)
		Int8 [] charPosRatio = readInt8s(data, 7); // ���� ��ġ (-100%~ 100%)
		
		HwpUnit baseCharSize = new HwpUnit(data);
		UInt32 fontProperty = new UInt32(data);
		Int8 shadowPosXRatio = new Int8(data);
		Int8 shadowPosYRatio = new Int8(data);
		
		ColorRef fontColor = new ColorRef(data);
		ColorRef underlineColor = new ColorRef(data);
//		ColorRef 
		return record;
	}
	
	private Int8 [] readInt8s(ByteBuffer data, final int size){
		Int8 [] is = new Int8[size];
		for (int i = 0; i < is.length; i++) {
			is[i] = new Int8(data);
		}
		return is;
	}
	private UInt8 [] readUInt8s(ByteBuffer data, final int size){
		UInt8 [] is = new UInt8[size];
		for (int i = 0; i < is.length; i++) {
			is[i] = new UInt8(data);
		}
		return is;
		
	}
	private Word [] decodeFontIds(ByteBuffer data, final int size){
		Word [] words = new Word[size];
		for( int i = 0 ;i < size ; i++){
			words[i] = new Word(data, data.position());
		}
		return words;
	}

	@Override
	public boolean isAvailable(String versionString) {
		// TEST created and not tested method stub
		return false;
	}

}
