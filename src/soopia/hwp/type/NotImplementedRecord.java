package soopia.hwp.type;

import soopia.hwp.type.stream.RecordHeader;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class NotImplementedRecord extends AbstractRecord {

	public NotImplementedRecord(RecordHeader header, IStreamStruct ds,
			int offset) {
		super(header, ds, offset);
	}

	@Override
	public String getTagName() {
		return "[NI] " + super.getTagName();
	}
}
