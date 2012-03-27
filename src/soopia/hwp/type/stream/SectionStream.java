package soopia.hwp.type.stream;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IRecordStructure;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class SectionStream extends AbstactStream {

	public SectionStream(HwpContext context, String structureName, byte [] data) {
		super(context, structureName, data);
	}

	public SectionStream(HwpContext context, byte [] data) {
		super( context, "Section", data);
	}

	public void addRecord(IRecordStructure record) {
		this.records.add(record);
	}

}
