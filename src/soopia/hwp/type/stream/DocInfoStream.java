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
public class DocInfoStream extends AbstactStream {
	
	public DocInfoStream(HwpContext context, byte [] data) {
		super(context, "DocInfo", data);
	}
	
	public void addRecord(IRecordStructure record){
		this.records.add(record);
	}
}