/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 */
package soopia.hwp.codec;

import soopia.hwp.type.HwpContext;
import soopia.hwp.type.HwpUnit;
import soopia.hwp.type.UInt32;
import soopia.hwp.type.UInt8;
import soopia.hwp.type.record.TabRecord;
import soopia.hwp.type.record.TabRecord.TabData;
import soopia.hwp.util.Converter;
import soopia.hwp.util.IByteSource;

/**
 * @author chmin
 *
 */
public class TabRecordDecoder extends AbstractRecordDecoder<TabRecord> {

	
	@Override
	public TabRecord decode(TabRecord record, IByteSource data,
			HwpContext context) throws DecodingException {
		// TEST created and not tested method stub
		super.decode(record, data, context);
		
		data.skip(4); // skip header
		record.setTabProperty(new UInt32(data.consume(4)));
		int count = Converter.getInt32(data.consume(4), 0);
		for( int i = 0 ; i < count ; i++){
			TabData tab = new TabData(new HwpUnit(data.consume(4)), 
					new UInt8(data.consume(1)),
					new UInt8(data.consume(1)));
			record.addTab(tab);
			data.consume(2); // padding (ǥ-31 �� ����)
		}
		
		return record;
	}

	@Override
	public boolean isAvailable(String versionString) {
		// TEST created and not tested method stub
		return false;
	}

}
