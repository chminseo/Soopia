package soopia.hwp.codec;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.stream.DocInfoStream;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class MockDocInfo extends DocInfoStream {
	
	public MockDocInfo(HwpContext context, byte [] data) {
//		super(context, "MockDocInfo", data);
		super(context, data);
		context.setDocInfo(this);
	}
}