package soopia.hwp.codec;

import soopia.hwp.type.AbstactStream;
import soopia.hwp.type.HwpContext;
/**
 * �� ��ǰ�� �ѱ۰���ǻ���� �ѱ� ���� ����(.hwp) ���� ������ �����Ͽ� �����Ͽ����ϴ�.
 * 
 * @author chmin
 *
 */
public class MockDocInfo extends AbstactStream {

	public MockDocInfo(HwpContext context, byte [] data) {
		super(context, "MockDocInfo", data);
	}
}