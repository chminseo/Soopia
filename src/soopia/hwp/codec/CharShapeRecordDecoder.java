/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 */
package soopia.hwp.codec;

import soopia.hwp.type.ColorRef;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.HwpUnit;
import soopia.hwp.type.Int8;
import soopia.hwp.type.UInt16;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.UInt8;
import soopia.hwp.type.Word;
import soopia.hwp.type.record.CharShapeRecord;
import soopia.hwp.util.IByteSource;

/**
 * @author chmin
 *
 */
public class CharShapeRecordDecoder extends AbstractRecordDecoder<CharShapeRecord> {

	/**
	 * 
	 */
	public CharShapeRecordDecoder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CharShapeRecord decode(CharShapeRecord record, IByteSource data,
			HwpContext context) throws DecodingException {
		// TEST created and not tested method stub
		super.decode(record, data, context); // ����Ʈ �迭 ���� ����
		
		int offset = record.getHeaderLength();

		data.skip(offset);
		record.setFontIds(decodeFontIds(data, 7));

		record.setCharWidthRatio(readUInt8s(data, 7)); // ���� ( 50% ~ 200% )
		record.setCharGapRatio( readInt8s(data, 7) ); // �ڰ� (-50% ~ 50% )
		record.setCharSizeRatio(readUInt8s(data, 7) ); // ������� ���� ũ��(10% ~ 250%)
		record.setCharPosRatio(readInt8s(data, 7)); // ���� ��ġ (-100%~ 100%)
		
		record.setBaseCharSize(new HwpUnit(data.consume(4)));// ���� ���� ũ��
		record.setFontProperty(new UInt32(data.consume(4)));// �Ӽ�
		record.setShadowPosXRatio(new Int8(data.consume(1)));
		record.setShadowPosYRation(new Int8(data.consume(1)));
		
		record.setFontColor( new ColorRef(data.consume(4)));
		record.setUnderlineColor( new ColorRef(data.consume(4)));
		record.setBackgroundColor( new ColorRef(data.consume(4)));
		record.setShadowColor(new ColorRef(data.consume(4)));
		record.setBorderFillId(new UInt16(data.consume(2)));
		record.setStrokeColor( new ColorRef(data.consume(4)));
		return record;
	}
	
	private Int8 [] readInt8s(IByteSource data, final int size){
		Int8 [] is = new Int8[size];
		for (int i = 0; i < is.length; i++) {
			is[i] = new Int8(data.consume(1));
		}
		return is;
	}
	private UInt8 [] readUInt8s(IByteSource data, final int size){
		UInt8 [] is = new UInt8[size];
		for (int i = 0; i < is.length; i++) {
			is[i] = new UInt8(data.consume(1));
		}
		return is;
		
	}
	private Word [] decodeFontIds(IByteSource data, final int size){
		Word [] words = new Word[size];
		for( int i = 0 ;i < size ; i++){
			words[i] = new Word(data.consume(2));
		}
		return words;
	}

	@Override
	public boolean isAvailable(String versionString) {
		// TEST created and not tested method stub
		return false;
	}

}
